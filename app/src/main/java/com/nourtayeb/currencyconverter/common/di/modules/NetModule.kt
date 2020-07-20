package com.nourtayeb.currencyconverter.common.di.modules

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nourtayeb.currencyconverter.clean_arch.data.remote.Api
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.io.File
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit


val HEADER_CACHE_CONTROL = "Cache-Control"
val HEADER_PRAGMA = "Pragma"
val TIMEOUT_REQUEST: Long = 30
@Module
class NetModule(private val baseUrl: String,private val context: Context) {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()



    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor{
                val response = it.proceed(it.request())
                val cacheControl: CacheControl
                cacheControl = CacheControl.Builder()
                    .maxAge(30, TimeUnit.MINUTES)
                    .minFresh(30, TimeUnit.MINUTES)
                    .maxStale(30, TimeUnit.MINUTES)
                    .build()

                response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
            .cache(provideCache())
            .connectTimeout(TIMEOUT_REQUEST, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT_REQUEST, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_REQUEST, TimeUnit.MINUTES)
            .build()

        return httpClient

    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Singleton
    fun provideRetrofitService(builder: Retrofit.Builder): Api = builder.baseUrl(baseUrl).build().create(Api::class.java)


    private fun provideCache(): Cache? {
        var cache: Cache? = null

        try {
            cache = Cache(
                File(context.getCacheDir(), "http-cache"),
                10 * 1024 * 1024
            ) // 10 MB
        } catch (e: Exception) {
            Log.e("NetModule", "Could not create Cache!")
        }

        return cache
    }



}