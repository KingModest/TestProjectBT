package com.kingmodest.testproject.network.util

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class ResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        check(returnType is ParameterizedType) {
            "Return type must be a parameterized type."
        }
        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != ApiState::class.java) {
            return null
        }
        check(responseType is ParameterizedType) {
            "Response type must be a parameterized type."
        }
        val errorType = getParameterUpperBound(1, responseType)
        if (getRawType(errorType) != NetworkError::class.java) {
            return null
        }
        val successBodyType = getParameterUpperBound(0,
            getParameterUpperBound(0, returnType) as ParameterizedType
        )
        return ResultCallAdapter<Any>(successBodyType)
    }
}