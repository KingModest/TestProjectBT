package com.kingmodest.testproject.ui.devicelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingmodest.testproject.data.datasource.device.DeviceDataSource
import com.kingmodest.testproject.domain.usecase.GetDevicesUseCase
import com.kingmodest.testproject.network.response.Device
import com.kingmodest.testproject.network.util.ApiState
import com.kingmodest.testproject.network.util.NetworkError
import com.kingmodest.testproject.network.util.asViewStateFlow
import com.kingmodest.testproject.ui.scan.ScanViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val getDevicesUseCase: GetDevicesUseCase,
    private val deviceDataSource: DeviceDataSource,
) : ViewModel() {

    private val _viewState = MutableStateFlow(DeviceListViewState())
    val viewState = _viewState.asStateFlow()

    init {
        getDevices()
    }

    private fun getDevices() {
        viewModelScope.launch {
            getDevicesUseCase().asViewStateFlow().collectLatest { state ->
                when (state) {
                    is ApiState.Success -> {
                        _viewState.value = DeviceListViewState(devices = state.data.devices)
                    }

                    is ApiState.Error -> {
                        _viewState.value = DeviceListViewState(
                            isLoading = false,
                            errorMessage = (state.error as? NetworkError.Server)?.errorMessage ?: "Error"
                        )
                    }

                    is ApiState.Loading -> {
                        _viewState.value = DeviceListViewState(isLoading = true)
                    }
                }
            }
        }
    }

    fun setCurrentDevice(device: Device) {
        deviceDataSource.setDevice(device)
    }
}