package com.kingmodest.testproject.domain.usecase

import com.kingmodest.testproject.data.repository.devices.DevicesRepository
import com.kingmodest.testproject.network.response.DeviceResponse
import com.kingmodest.testproject.network.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDevicesUseCase @Inject constructor(
    private val devicesRepository: DevicesRepository,
) {
    operator fun invoke(): Flow<ApiResult<DeviceResponse>> {
        return devicesRepository.getDevices()
    }
}