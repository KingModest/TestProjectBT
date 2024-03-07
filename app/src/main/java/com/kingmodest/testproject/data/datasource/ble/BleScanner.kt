package com.kingmodest.testproject.data.datasource.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED
import androidx.core.content.ContextCompat.registerReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BleScanner @Inject constructor() {

    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
    private var scanning = false
    private val handler = Handler()

    // Stops scanning after 10 seconds.
    private val SCAN_PERIOD: Long = 10000
    private var _onNewDevice: (BluetoothDevice) -> Unit = {}

    // Device scan callback.
    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            _onNewDevice(result.device)
        }
    }

    @SuppressLint("MissingPermission")
    fun scanLeDevice(onNewDevice: (BluetoothDevice) -> Unit) {
        _onNewDevice = onNewDevice
        if (!scanning) { // Stops scanning after a pre-defined scan period.
            handler.postDelayed({
                scanning = false
                bluetoothLeScanner.stopScan(leScanCallback)
            }, SCAN_PERIOD)
            scanning = true
            bluetoothLeScanner.startScan(leScanCallback)
        } else {
            scanning = false
            bluetoothLeScanner.stopScan(leScanCallback)
        }
    }

}