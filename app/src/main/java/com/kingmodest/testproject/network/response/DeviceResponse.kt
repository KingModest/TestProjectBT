package com.kingmodest.testproject.network.response

import androidx.compose.runtime.Immutable

@Immutable
data class DeviceResponse(val devices: List<Device>)

@Immutable
data class Device(
    val macAddress: String,
    val model: String,
    val product: String?,
    val firmwareVersion: String,
    val serial: String?,
    val installationMode: String?,
    val brakeLight: Boolean,
    val lightMode: String?,
    val lightAuto: Boolean,
    val lightValue: Int,
)