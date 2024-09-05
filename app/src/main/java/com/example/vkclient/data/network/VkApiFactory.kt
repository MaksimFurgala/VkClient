package com.example.vkclient.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Vk api factory
 *
 * @constructor Create empty Vk api factory
 */
object VkApiFactory {

    /**
     * Базовый URL для запросов к сервису VK.
     */
    private const val baseUrl = "https://api.vk.com/method/"

    /**
     * Ok http клиент для последующего логирования REST Api запросов.
     */
    private val okHttpClient =
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .build()

    /**
     * Retrofit.
     */
    private val retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    /**
     * Vk api service.
     */
    val vkApiService = retrofit.create(VkApiService::class.java)
}