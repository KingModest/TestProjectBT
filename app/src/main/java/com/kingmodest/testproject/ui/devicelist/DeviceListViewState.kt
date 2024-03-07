package com.kingmodest.testproject.ui.devicelist

import androidx.compose.runtime.Immutable
import com.kingmodest.testproject.network.response.Device

@Immutable
data class DeviceListViewState(
    val devices: List<Device> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)