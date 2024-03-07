package com.kingmodest.testproject.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kingmodest.testproject.ui.devicedetails.DeviceDetailScreen
import com.kingmodest.testproject.ui.devicelist.DeviceListScreen
import com.kingmodest.testproject.ui.scan.ScanScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            NavHost(navController, startDestination = "list") {
                composable(route = "list") {
                    DeviceListScreen(
                        onDeviceItemClick = { navController.navigate("detail") },
                        onScanClick = { navController.navigate("scan") }
                    )
                }
                composable(route = "detail") {
                    DeviceDetailScreen(
                        onBackNavigation = { navController.navigateUp() }
                    )
                }
                composable(route = "scan") {
                    ScanScreen()
                }
            }
        }
    }
}