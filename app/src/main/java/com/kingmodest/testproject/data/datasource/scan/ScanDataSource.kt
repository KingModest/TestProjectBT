package com.kingmodest.testproject.data.datasource.scan

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.content.Context
import android.util.Log
import com.kingmodest.testproject.data.datasource.ble.BleScanner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class ScanDataSource @Inject constructor(
    private val context: Context,
    private val bleScanner: BleScanner,
) : IScanDataSource {

    private val _scanDeviceList = MutableStateFlow(BleDevices())
    private val scanDeviceList = _scanDeviceList.asStateFlow()

    override fun provideScanDevices(): StateFlow<BleDevices> {
        _scanDeviceList.value.devices.clear()
        bleScanner.scanLeDevice {
            if (it !in _scanDeviceList.value.devices) {
                val devices = mutableListOf<BluetoothDevice>()
                devices.addAll(_scanDeviceList.value.devices)
                devices.add(it)
                _scanDeviceList.value = _scanDeviceList.value.copy(devices = devices)
            }
        }
        return scanDeviceList
    }

    @SuppressLint("MissingPermission")
    override fun connectTo(device: BluetoothDevice) {
        device.connectGatt(context, false,
            object : BluetoothGattCallback() {
                override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                    val services = gatt.services
                    Log.i("BLE", "Services: $services")
                    gatt.readCharacteristic(services[0].characteristics[0])
                }

                override fun onCharacteristicRead(
                    gatt: BluetoothGatt,
                    characteristic: BluetoothGattCharacteristic,
                    value: ByteArray,
                    status: Int,
                ) {
                    super.onCharacteristicRead(gatt, characteristic, value, status)
                    val charValue = characteristic.value
                    val flag = charValue[0]
                    Log.i("BLE", "Characteristic: $flag")
                    // add callback to display characteristic on UI
                }
            })
    }

    data class BleDevices(
        val devices: MutableList<BluetoothDevice> = mutableListOf(),
    )

}