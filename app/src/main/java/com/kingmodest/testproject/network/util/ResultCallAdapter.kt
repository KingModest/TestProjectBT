package com.kingmodest.testproject.network.util

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResultCallAdapter<T>(
    private val successType: Type
) : CallAdapter<T, Call<ApiResult<T>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> = ResultCall(call, successType)
}