package net.joneserick.noterin.network.base

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ServiceFactory<T>(
    clazz: Class<T>,
    baseUrl: String
): BaseFactory<T>(clazz, baseUrl){

    companion object {
        const val DEFAULT_TIME_IN_MINUTES :Long = 2
        const val DEFAULT_TIMEOUT_IN_SECONDS :Long = 30
    }

    override fun createOkhttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .writeTimeout(DEFAULT_TIME_IN_MINUTES, TimeUnit.MINUTES)
            .readTimeout(DEFAULT_TIME_IN_MINUTES, TimeUnit.MINUTES)
            .connectTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
    }

}