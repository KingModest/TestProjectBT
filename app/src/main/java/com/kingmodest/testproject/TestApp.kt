package com.kingmodest.testproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope

@HiltAndroidApp
class TestApp : Application() {

    companion object {
        private var applicationScope = MainScope()
    }
}