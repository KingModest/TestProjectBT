package com.kingmodest.testproject.network.service

import com.kingmodest.testproject.network.response.DeviceResponse
import com.kingmodest.testproject.network.util.ApiResult
import retrofit2.http.GET

interface DeviceService {

    @GET("devices")
    suspend fun getDevices(): ApiResult<DeviceResponse>
}