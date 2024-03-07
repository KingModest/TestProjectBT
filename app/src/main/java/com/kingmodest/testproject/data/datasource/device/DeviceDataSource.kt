package com.kingmodest.testproject.data.datasource.device

import com.kingmodest.testproject.data.datasource.scan.IScanDataSource
import com.kingmodest.testproject.network.response.Device
import javax.inject.Inject

class DeviceDataSource @Inject constructor() : IDeviceDataSource {

    private var currentDevice: Device? = null

    override fun setDevice(device: Device) {
        currentDevice = device
    }

    override fun getDevice(): Device? {
        return currentDevice
    }


}