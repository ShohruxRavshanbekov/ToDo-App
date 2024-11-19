package uz.futuresoft.tasks.utils

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import uz.futuresoft.core.receivers.NetworkChangeReceiver

@Composable
fun NetworkChangeHandler(
    lifecycleOwner: LifecycleOwner,
    onNetworkAvailable: () -> Unit,
    onNetworkUnavailable: () -> Unit = {},
) {
    val context = LocalContext.current
    val networkReceiver = remember {
        NetworkChangeReceiver(
            onNetworkAvailable = onNetworkAvailable,
            onNetworkUnavailable = onNetworkUnavailable,
        )
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            if (event == Lifecycle.Event.ON_START) {
                context.registerReceiver(networkReceiver, intentFilter)
            } else if (event == Lifecycle.Event.ON_STOP) {
                context.unregisterReceiver(networkReceiver)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer = observer)

        onDispose { lifecycleOwner.lifecycle.removeObserver(observer = observer) }
    }
}