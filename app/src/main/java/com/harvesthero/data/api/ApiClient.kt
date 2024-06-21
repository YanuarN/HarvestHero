package com.harvesthero.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val CORN_BASE_URL = "https://corn-sfab57tqla-et.a.run.app/"
    private const val PADI_BASE_URL = "https://padi-sfab57tqla-et.a.run.app/"
    private const val BANANA_BASE_URL = "https://banana-api-sfab57tqla-et.a.run.app/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val corn_retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(CORN_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val padi_Retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(PADI_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val banana_Retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BANANA_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cornApiService: ApiService by lazy {
        corn_retrofit.create(ApiService::class.java)
    }

    val padiApiService: ApiService by lazy {
        padi_Retrofit.create(ApiService::class.java)
    }

    val bananaApiService: ApiService by lazy {
        banana_Retrofit.create(ApiService::class.java)
    }
}
