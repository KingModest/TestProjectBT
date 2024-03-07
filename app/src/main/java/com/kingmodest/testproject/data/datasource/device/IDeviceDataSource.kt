package com.kingmodest.testproject.data.datasource.device

import com.kingmodest.testproject.network.response.Device

interface IDeviceDataSource {

    fun setDevice(device: Device)

    fun getDevice(): Device?
}