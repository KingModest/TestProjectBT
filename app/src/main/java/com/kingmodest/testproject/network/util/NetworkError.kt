package com.kingmodest.testproject.network.util

sealed class NetworkError : TypedError() {
    data object Connection : NetworkError()
    data class Server(val code: Int, val errorMessage: String? = null) : NetworkError()
    data class Unexpected(val throwable: Throwable) : NetworkError()
}

abstract class TypedError : Throwable()