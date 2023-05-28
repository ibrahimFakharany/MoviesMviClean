package com.ibrahim.remotedatasource.service

import com.ibrahim.remotedatasource.BuildConfig
import com.ibrahim.remotedatasource.utils.AppResponseAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit

object MoviesServiceFactory {
    fun makeMoviesService(isDebug: Boolean): MoviesService {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor(isDebug))
        return makeMoviesService(okHttpClient)
    }

    private fun makeMoviesService(okHttpClient: OkHttpClient): MoviesService {
        val moshi = Moshi.Builder().build()

        val retrofit =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(AppResponseAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        return retrofit.create(MoviesService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val newUrl = original.url.newBuilder()
                .addQueryParameter("api_key", "d7a6a216cd37824fc6cc679c91bbafb8")
                .addQueryParameter("language", Locale.getDefault().toLanguageTag()).build()
            chain.proceed(original.newBuilder().url(newUrl).build())
        }.addInterceptor(httpLoggingInterceptor).connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build()
    }
//    KotlinJsonAdapterFactory

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
