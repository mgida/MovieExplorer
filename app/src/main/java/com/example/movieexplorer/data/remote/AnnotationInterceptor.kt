package com.example.movieexplorer.data.remote

import com.example.movieexplorer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation

private const val AUTH_HEADER = "Authorization"

class AnnotationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())
        containedOnInvocation(invocation).forEach { annotation ->
            request = handleAnnotation(annotation, request)
        }
        return chain.proceed(request)
    }

    private fun handleAnnotation(annotation: Annotation, request: Request): Request {
        return when (annotation) {
            is Authorized -> {
                addAuthHeader(request)
            }

            else -> request
        }
    }

    private fun addAuthHeader(request: Request): Request {
        return request.newBuilder().addHeader(AUTH_HEADER, API_KEY).build()
    }

    private fun containedOnInvocation(invocation: Invocation): Set<Annotation> {
        return invocation.method().annotations.toSet()
    }

    companion object {
        private const val TOKEN = "Bearer"
        private const val API_KEY = "$TOKEN ${BuildConfig.API_KEY}"
    }
}