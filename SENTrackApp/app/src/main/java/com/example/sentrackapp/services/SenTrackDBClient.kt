package com.example.sentrackapp.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "199be4089d99e0bede713bf5babe5183"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

const val POPULAR_MOVIE_PATH = "movie/popular"

const val FIRST_PAGE = 1
const val TOTAL_PAGE = 20

object SenTrackDBClient {

    private val requestInterceptor = Interceptor{
        val url = it.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = it.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor it.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
                                .addInterceptor(requestInterceptor)
                                .connectTimeout(60,TimeUnit.SECONDS)
                                .build()

    private val retrofitClient = Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build()

    fun <T> buildService(serviceType: Class<T>): T = retrofitClient.create(serviceType)

}