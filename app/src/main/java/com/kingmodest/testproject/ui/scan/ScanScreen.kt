package com.kingmodest.testproject.ui.scan

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kingmodest.testproject.ui.component.MessageView

@Composable
fun ScanScreen(viewModel: ScanViewModel = hiltViewModel()) {
    val viewState by viewModel.viewState.collectAsState()
    ScanPage(viewState) { viewModel.connectTo(it) }
}

@SuppressLint("MissingPermission")
@Composable
fun ScanPage(viewState: ScanViewState, onConnect: (BluetoothDevice) -> Unit) {
    Scaffold { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .padding(all = 20.dp)
                .fillMaxHeight()
        ) {
            LazyColumn {
                items(viewState.devices) {
                    Column(modifier = Modifier.clickable { onConnect(it) }) {
                        it.name?.let {
                            Text(text = "Name", fontSize = 18.sp)
                            Text(text = it)
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                        Text(text = "Address", fontSize = 18.sp)
                        Text(text = it.address)
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = Color.Black)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (viewState.errorMessage != null) {
                MessageView("Network error, please contact support.", isError = true)
            }
        }
    }
}

@Preview
@Composable
fun ScanPagePreview() {
    ScanPage(ScanViewState()) { }
}