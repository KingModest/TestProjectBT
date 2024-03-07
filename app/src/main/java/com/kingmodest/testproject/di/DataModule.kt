package com.kingmodest.testproject.di

import android.content.Context
import com.kingmodest.testproject.data.datasource.ble.BleScanner
import com.kingmodest.testproject.data.datasource.device.DeviceDataSource
import com.kingmodest.testproject.data.datasource.scan.ScanDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideDeviceRepository(): DeviceDataSource = DeviceDataSource()

    @Provides
    @Singleton
    fun provideBleScanner(): BleScanner = BleScanner()

    @Provides
    @Singleton
    fun provideScanRepository(context: Context, bleScanner: BleScanner): ScanDataSource =
        ScanDataSource(context, bleScanner)
}