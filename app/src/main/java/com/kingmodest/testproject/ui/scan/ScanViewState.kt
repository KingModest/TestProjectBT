package com.kingmodest.testproject.ui.scan

import android.bluetooth.BluetoothDevice
import androidx.compose.runtime.Immutable
import com.kingmodest.testproject.network.response.Device

@Immutable
data class ScanViewState(
    val devices: List<BluetoothDevice> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)