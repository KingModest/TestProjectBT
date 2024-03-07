package com.kingmodest.testproject.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kingmodest.testproject.R
import kotlinx.coroutines.delay

@Composable
fun MessageView(messageText: String, onHideAction: () -> Unit = {}, isError: Boolean = false) {
    val state = remember { MutableTransitionState(false).apply { targetState = true } }
    LaunchedEffect(key1 = Unit) {
        delay(4000)
        state.targetState = false
        onHideAction()
    }
    AnimatedVisibility(visibleState = state, enter = fadeIn(), exit = fadeOut()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(
                        color = getMessageColor(isError),
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(20.dp, 10.dp, 0.dp, 10.dp),
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = null,
                    tint = colorResource(id = R.color.white)
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = messageText,
                    color = colorResource(id = R.color.white)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun getMessageColor(isError: Boolean): Color {
    return if (isError) {
        colorResource(id = R.color.error_container)
    } else {
        colorResource(id = R.color.message_view_success)
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    MessageView("Network error, please contact support.", {}, true)
}

@Preview
@Composable
fun MessageViewPreview() {
    MessageView(
        "Location not found, a new location was created. VALPN-00100003 saved to CTR1-1-1-1-A.",
        {},
        false
    )
}