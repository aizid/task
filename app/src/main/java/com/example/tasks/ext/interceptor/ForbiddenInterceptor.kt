package com.example.tasks.ext.interceptor

import kotlin.Throws
import com.example.tasks.ext.exeption.ForbiddenException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection

class ForbiddenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        if (response.code == HttpURLConnection.HTTP_FORBIDDEN) {
            throw ForbiddenException()
        }
        return response
    }
}