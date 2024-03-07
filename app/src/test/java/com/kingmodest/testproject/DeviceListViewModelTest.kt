package com.kingmodest.testproject

import com.kingmodest.testproject.common.CoroutineTestRule
import com.kingmodest.testproject.data.datasource.device.DeviceDataSource
import com.kingmodest.testproject.domain.usecase.GetDevicesUseCase
import com.kingmodest.testproject.ui.devicelist.DeviceListViewModel
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DeviceListViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val getDevicesUseCase = mockk<GetDevicesUseCase>()
    private val deviceDataSource = mockk<DeviceDataSource>()

    @Test
    fun `test loading`() = runTest {
        // given
        every { getDevicesUseCase() } returns flow { }
        val viewModel =
            DeviceListViewModel(
                getDevicesUseCase,
                deviceDataSource
            )

        // when
        val collectJob = launch(coroutineTestRule.dispatcher) { viewModel.viewState.collect() }

        // then
        viewModel.viewState.value.isLoading shouldBe true
        viewModel.viewState.value.errorMessage shouldBe null
        collectJob.cancel()
    }

    // Add test for failing and retrieving correct data
}