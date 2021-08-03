package net.joneserick.noterin.network.base

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseFactory<ClassType>(
    private val clazz: Class<ClassType>,
    private val baseUrl: String
) {

    private val api: ClassType = createApiReference()

    private val retrofitBuilder = createRetrofitBuilder()

    protected abstract fun createOkhttpClientBuilder(): OkHttpClient.Builder

    protected fun getApi(): ClassType = api

    private fun createApiReference(): ClassType {
        val clientBuilder = createOkhttpClientBuilder()
        return retrofitBuilder.client(clientBuilder.build()).build().create(clazz)
    }

    private fun createRetrofitBuilder(): Retrofit.Builder {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }
}