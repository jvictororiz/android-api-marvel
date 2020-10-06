package com.joaororiz.desafio.android.base

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseServiceTest : BaseTest() {
    protected fun readJson(file: String): String? =
            this::class.java.getResourceAsStream(file)?.readBytes()?.toString(Charsets.UTF_8)

}
