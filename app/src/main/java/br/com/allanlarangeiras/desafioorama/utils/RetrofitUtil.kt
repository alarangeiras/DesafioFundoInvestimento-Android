package br.com.allanlarangeiras.desafioorama.utils

import br.com.allanlarangeiras.desafioorama.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object RetrofitUtil {

    fun <T> buildOramaProxy(classType: Class<T>): T {
        return buildRetrofit(BuildConfig.ORAMA_BASE_URL)
            .create(classType)
    }

    private fun buildRetrofit(baseUrl: String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}