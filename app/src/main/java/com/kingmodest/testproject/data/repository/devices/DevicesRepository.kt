package com.kingmodest.testproject.data.repository.devices

import com.kingmodest.testproject.network.service.DeviceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DevicesRepository @Inject constructor(
    private val deviceService: DeviceService,
) : IDevicesRepository {

    override fun getDevices() = flow {
        emit(deviceService.getDevices())
    }.flowOn(Dispatchers.IO)
}