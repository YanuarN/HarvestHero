package com.harvesthero.predict

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.harvesthero.R
import com.harvesthero.adapter.SpinnerAdapter
import com.harvesthero.adapter.SpinnerItem
import com.harvesthero.data.api.ApiClient
import com.harvesthero.data.response.PredictionResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class CameraActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private var imageCapture: ImageCapture? = null
    private var plantType: String? = null // Add a field to store the plant type
    private lateinit var plantTypeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        previewView = findViewById(R.id.previewView)
        val captureButton: ImageButton = findViewById(R.id.buttonSnap)
        val galleryButton: ImageButton = findViewById(R.id.buttonGallery)
        plantTypeSpinner = findViewById(R.id.plant_type_spinner)

        findViewById<ImageView>(R.id.buttonSnapTipsBottom).setOnClickListener {
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }

        // Setup Spinner
        val items = listOf(
            SpinnerItem(R.drawable.ic_spinner_arrow, "Padi"),
            SpinnerItem(R.drawable.ic_spinner_arrow, "Banana"),
            SpinnerItem(R.drawable.ic_spinner_arrow, "Corn")
        )
        val adapter = SpinnerAdapter(this, items)
        plantTypeSpinner.adapter = adapter

        // Retrieve the plant type from the intent
        plantType = intent.getStringExtra("plant_type")

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions.launch(REQUIRED_PERMISSIONS)
        }

        captureButton.setOnClickListener { takePhoto() }
        galleryButton.setOnClickListener { openGallery() }
    }

    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                finish() // If permissions are not granted, close the activity
            }
        }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Preview
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            // ImageCapture
            imageCapture = ImageCapture.Builder().build()

            // Select back camera as default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                // Handle exception
                Toast.makeText(this, "Failed to bind camera use cases", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        // Create time-stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CameraX-Image")
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        // Set up image capture listener, which is triggered after photo has been taken
        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(baseContext, "Photo capture failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    output.savedUri?.let { uri ->
                        sendPredictionRequest(uri)
                    }
                }
            }
        )
    }

    private fun sendPredictionRequest(imageUri: Uri) {
        val imageFile = File(getRealPathFromURI(imageUri))
        if (!imageFile.exists()) {
            Log.e("sendPredictionRequest", "File does not exist: ${imageFile.path}")
            return
        }
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
        val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        Log.d("sendPredictionRequest", "Sending prediction request for file: ${imageFile.name}")

        val call = when (plantType) {
            "padi" -> ApiClient.padiApiService.getPadiPrediction(body)
            "banana" -> ApiClient.bananaApiService.getBananaPrediction(body)
            else -> ApiClient.cornApiService.getCornPrediction(body)  // Default to corn
        }

        call.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                Log.d("PredictionResponse", "Response code: ${response.code()}")
                if (response.isSuccessful) {
                    val prediction = response.body()
                    Log.d("PredictionResponse", "Response body: ${response.body()}")
                    prediction?.let {
                        showPredictionResult(it.data.result, it.data.suggestion.joinToString("\n"), imageUri, response.code(), response.message())
                    }
                } else {
                    Log.e("PredictionResponse", "Failed to get prediction: ${response.message()}")
                    Log.e("PredictionResponse", "Response error body: ${response.errorBody()?.string()}")
                    showPredictionResult("Unknown Disease", "No recommendation available", imageUri, response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                Log.e("PredictionResponse", "Error: ${t.message}")
                Toast.makeText(this@CameraActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showPredictionResult(disease: String, recommendation: String, imageUri: Uri, responseCode: Int, responseMessage: String) {
        val intent = Intent(this, RecommendActivity::class.java).apply {
            putExtra("disease", disease)
            putExtra("recommendation", recommendation)
            putExtra("image_uri", imageUri.toString())
            putExtra("response_code", responseCode.toString())
            putExtra("response_message", responseMessage)
        }
        startActivity(intent)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryResultLauncher.launch(intent)
    }

    private val galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.data?.let { uri ->
                sendPredictionRequest(uri)
            }
        }
    }

    private fun getRealPathFromURI(contentUri: Uri): String {
        var result: String
        val cursor = contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            result = contentUri.path ?: ""
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}
