package com.kingmodest.testproject.ui.devicedetails

import androidx.compose.runtime.Immutable
import com.kingmodest.testproject.network.response.Device

@Immutable
data class DeviceDetailViewState(
    val device: Device? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)