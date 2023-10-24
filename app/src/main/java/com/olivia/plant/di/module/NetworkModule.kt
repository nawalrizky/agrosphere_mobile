package com.olivia.plant.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.olivia.plant.data.network.ApiEndpoint
import com.olivia.plant.BuildConfig
import com.olivia.plant.data.network.EspEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val networkModule = module {
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single(named("esp")) { provideRetrofitEsp(get(), "http://192.168.18.205/") }
    single(named("service_esp")) { provideEspService(get(named("esp"))) }
}

private fun provideOkHttpClient(context: Context): OkHttpClient {
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addInterceptor(loggingInterceptor)
            .build()
    } else return OkHttpClient
        .Builder()
        .build()
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideRetrofitEsp(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiEndpoint =
    retrofit.create(ApiEndpoint::class.java)

private fun provideEspService(retrofit: Retrofit): EspEndpoint =
    retrofit.create(EspEndpoint::class.java)
