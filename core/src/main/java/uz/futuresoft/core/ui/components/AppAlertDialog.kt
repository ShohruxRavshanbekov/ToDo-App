package uz.futuresoft.core.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun AppAlertDialog(
    messageText: String,
    confirmText: String,
    dismissText: String,
    messageTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    confirmTextColor: Color = MaterialTheme.colorScheme.primary,
    dismissTextColor: Color = MaterialTheme.colorScheme.primary,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(text = messageText)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirmText, color = confirmTextColor)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissText, color = dismissTextColor)
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.onBackground,
        textContentColor = messageTextColor,
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = false),
    )
}

@PreviewLightDark
@Composable
private fun AppAlertDialogPreview() {
    TodoAppTheme {
        AppAlertDialog(
            messageText = "Вы действительно хотите удалить?",
            confirmText = "Удалить",
            dismissText = "Отменить",
            confirmTextColor = MaterialTheme.colorScheme.error,
        )
    }
}