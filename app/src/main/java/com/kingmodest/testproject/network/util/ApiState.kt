package com.kingmodest.testproject.network.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

typealias ApiResult<T> = ApiState<T, NetworkError>

sealed class ApiState<out T, out E : TypedError> {
    data object Loading : ApiState<Nothing, Nothing>()
    data class Success<T>(val data: T) : ApiState<T, Nothing>()
    data class Error<E : TypedError>(val error: E) : ApiState<Nothing, E>()
}

fun <T, E : TypedError> Flow<ApiState<T, E>>.asViewStateFlow(): Flow<ApiState<T, E>> {
    return onStart { emit(ApiState.Loading) }
}