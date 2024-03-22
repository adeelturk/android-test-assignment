package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.BuildConfig
import com.example.shacklehotelbuddy.core.networks.RequestInterceptor
import com.example.shacklehotelbuddy.data.remote.HotelDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor()
    }

    @Provides
    @Singleton
    fun provideRetrofit(requestInterceptor: RequestInterceptor): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val logger =
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        httpClient.addInterceptor(requestInterceptor)
        httpClient.addInterceptor(logger)
        val retroBuilder =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        retroBuilder.client(
            httpClient.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build()
        )
        return retroBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHotelDataServiceWebservices(retrofit: Retrofit): HotelDataService {
        return retrofit.create(HotelDataService::class.java)
    }


}