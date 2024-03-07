package com.kingmodest.testproject.ui.scan

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingmodest.testproject.data.datasource.scan.ScanDataSource
import com.kingmodest.testproject.domain.usecase.GetDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scanDataSource: ScanDataSource
) : ViewModel() {

    private val _viewState = MutableStateFlow(ScanViewState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            scanDataSource.provideScanDevices().collect {
                _viewState.value = ScanViewState(devices = it.devices)
            }
        }
    }

    fun connectTo(device: BluetoothDevice) {
        scanDataSource.connectTo(device)
    }
}