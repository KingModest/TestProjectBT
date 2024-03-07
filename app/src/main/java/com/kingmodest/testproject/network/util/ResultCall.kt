package com.kingmodest.testproject.network.util

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type

internal class ResultCall<T>(
    private val delegate: Call<T>,
    private val successType: Type,
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) =
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@ResultCall, Response.success(response.toNetworkResult()))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(this@ResultCall, Response.success(throwable.toNetworkResult()))
            }

            private fun Response<T>.toNetworkResult(): ApiResult<T> {
                if (!isSuccessful)
                    return ApiState.Error(NetworkError.Server(code(), errorBody()?.string()))
                return if (code() in 200..300 && body() != null) {
                    ApiState.Success(body()!!)
                } else if (code() in 200..300 && body() == null) {
                    ApiState.Success(Unit as T)
                } else {
                    ApiState.Error(NetworkError.Server(code()))
                }
            }

            private fun Throwable.toNetworkResult(): ApiState.Error<NetworkError> {
                return ApiState.Error(
                    when (this) {
                        is IOException -> NetworkError.Connection
                        else -> NetworkError.Unexpected(this)
                    }
                )
            }
        })

    override fun clone(): Call<ApiResult<T>> = ResultCall(delegate, successType)

    override fun execute(): Response<ApiResult<T>> {
        throw UnsupportedOperationException("ResultCall doesn't support execute().")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}