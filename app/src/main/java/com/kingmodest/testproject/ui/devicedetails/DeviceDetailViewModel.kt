package com.kingmodest.testproject.ui.devicedetails

import androidx.lifecycle.ViewModel
import com.kingmodest.testproject.data.datasource.device.DeviceDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DeviceDetailViewModel @Inject constructor(
    deviceDataSource: DeviceDataSource,
) : ViewModel() {

    private val _viewState = MutableStateFlow(DeviceDetailViewState())
    val viewState = _viewState.asStateFlow()

    init {
        _viewState.value = DeviceDetailViewState(deviceDataSource.getDevice())
    }
}