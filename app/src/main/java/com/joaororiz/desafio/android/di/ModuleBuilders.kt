package com.joaororiz.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.joaororiz.desafio.android.extension.md5
import com.joaororiz.desafio.android.repository.datasource.character.local.database.LocalDatabase
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT: Long = 15L
private const val READ_TIMEOUT: Long = 15L


fun picasso(context: Context, downloader: OkHttpClient): Picasso =
    Picasso.Builder(context)
        .downloader(OkHttp3Downloader(downloader))
        .loggingEnabled(true)
        .build()

fun  buildWebService(baseUrl: String, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(httpClient)
        .build()
}

fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor {
            val ts = Date().time.toString()
            val apiKey = "916330231d85c1fac17c72bdda7132b3"
            val privateKey = "bf8a5721c65c0d4d6c9be7fefae3fa5ba67f8d7d"


            var request = it.request()
            val url: HttpUrl = request.url.newBuilder()
                .addQueryParameter("ts", ts)
                .addQueryParameter("apikey", apiKey)
                .addQueryParameter("hash", (ts + privateKey + apiKey).md5())

                .build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    }.build()
}


fun databaseBuilder(context: Context) =
    Room.databaseBuilder(context, LocalDatabase::class.java, "LocalDatabase")
    .fallbackToDestructiveMigration()
    .build()