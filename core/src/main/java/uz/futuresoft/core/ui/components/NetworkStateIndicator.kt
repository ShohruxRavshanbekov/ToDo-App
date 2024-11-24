package uz.futuresoft.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NetworkStateIndicator(isNetworkAvailable: Boolean?, modifier: Modifier) {
    AnimatedVisibility(
        visible = isNetworkAvailable == false,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Box(
            modifier = modifier.background(color = MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Нет связи с интернетом, данные могут быть неактуальным!",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onError,
            )
        }
    }
}