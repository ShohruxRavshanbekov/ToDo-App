@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.components.VerticalSpacer
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.utils.formatDateMillisTo

@Composable
fun TaskDueDateView(
    showCalendar: Boolean,
    initialSelectedDateMillis: Long? = null,
    onDateSelected: (Long?) -> Unit,
) {
    var isChecked by remember { mutableStateOf(showCalendar) }
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = initialSelectedDateMillis)
    val selectedDate = datePickerState.selectedDateMillis?.formatDateMillisTo("d MMM yyyy")

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "Сделать до",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                AnimatedContent(
                    targetState = isChecked && !selectedDate.isNullOrEmpty(),
                    label = "selectedDate",
                    modifier = Modifier.padding(vertical = 2.dp)
                ) { state ->
                    if (state) {
                        Text(
                            text = selectedDate!!,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                        onDateSelected(datePickerState.selectedDateMillis)
                    } else {
                        onDateSelected(null)
                    }
                }
            }
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
            )
        }
        AnimatedContent(
            targetState = isChecked,
            label = "showCalendar",
        ) { state ->
            if (state) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    thickness = 0.5.dp,
                )
                DatePicker(
                    modifier = Modifier.fillMaxWidth(),
                    state = datePickerState,
                    showModeToggle = false,
                    title = null,
                    headline = null,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        navigationContentColor = MaterialTheme.colorScheme.secondary,
                        todayContentColor = MaterialTheme.colorScheme.secondary,
                        todayDateBorderColor = MaterialTheme.colorScheme.secondary,
                        selectedYearContentColor = MaterialTheme.colorScheme.secondary,
                        selectedDayContainerColor = MaterialTheme.colorScheme.secondary.copy(
                            alpha = 0.5f
                        ),
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TaskDueDateViewPreview() {
    TodoAppTheme {
        TaskDueDateView(showCalendar = false, onDateSelected = {})
    }
}