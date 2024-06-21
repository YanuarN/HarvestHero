package com.harvesthero.data.api

import com.harvesthero.data.response.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("predict")
    fun getCornPrediction(@Part image: MultipartBody.Part): Call<PredictionResponse>

    @Multipart
    @POST("predict")
    fun getPadiPrediction(@Part image: MultipartBody.Part): Call<PredictionResponse>

    @Multipart
    @POST("predict")
    fun getBananaPrediction(@Part image: MultipartBody.Part): Call<PredictionResponse>
}