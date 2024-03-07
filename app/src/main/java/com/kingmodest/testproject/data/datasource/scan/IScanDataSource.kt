package com.kingmodest.testproject.data.datasource.scan

import android.bluetooth.BluetoothDevice
import kotlinx.coroutines.flow.StateFlow

interface IScanDataSource {
    fun provideScanDevices(): StateFlow<ScanDataSource.BleDevices>
    fun connectTo(device: BluetoothDevice)
}