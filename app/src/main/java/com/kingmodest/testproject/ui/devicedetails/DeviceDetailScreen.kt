package com.kingmodest.testproject.ui.devicedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kingmodest.testproject.R
import com.kingmodest.testproject.ui.component.MessageView

@Composable
fun DeviceDetailScreen(
    viewModel: DeviceDetailViewModel = hiltViewModel(),
    onBackNavigation: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    DeviceDetailPage(viewState, onBackNavigation)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailPage(
    viewState: DeviceDetailViewState,
    onBackNavigation: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200),
                    titleContentColor = colorResource(id = R.color.white),
                ),
                title = {
                    Text("Device details")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackNavigation()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = colorResource(id = R.color.white)
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        Column(
            Modifier
                .padding(contentPadding)
                .fillMaxHeight(),
        ) {
            viewState.device?.let {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = it.model,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    it.product?.let {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Product: ", fontSize = 18.sp)
                        Text(text = it, fontSize = 16.sp)
                    }
                    it.serial?.let {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Serial number: ", fontSize = 18.sp)
                        Text(text = it)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Firmware version: ", fontSize = 18.sp)
                    Text(text = it.firmwareVersion)
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
fun DeviceDetailPagePreview() {
    DeviceDetailPage(DeviceDetailViewState()) {}
}