package com.kingmodest.testproject.di

import com.kingmodest.testproject.network.service.DeviceService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kingmodest.testproject.network.util.ResultCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().also { client ->
            client.addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .also { builder ->
                            builder.addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                        }
                        .build()
                )
            }
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            client.addInterceptor(logging)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cosmo-api.develop-sr3snxi-x6u2x52ooksf4.de-2.platformsh.site/test/")
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): DeviceService {
        return retrofit.create(DeviceService::class.java)
    }
}