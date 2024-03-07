package com.kingmodest.testproject.data.repository.devices

import com.kingmodest.testproject.network.response.DeviceResponse
import com.kingmodest.testproject.network.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface IDevicesRepository {

    fun getDevices(): Flow<ApiResult<DeviceResponse>>
}