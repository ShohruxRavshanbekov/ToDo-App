@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.task_events

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.tasks.common.models.Importance
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.task_events.components.Importance
import uz.futuresoft.tasks.utils.convertMillisToDate
import uz.futuresoft.tasks.utils.formatDateMillisTo
import uz.futuresoft.ui.components.VerticalSpacer
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.ChevronLeft
import uz.futuresoft.ui.theme.ToDoAppTheme
import java.util.Calendar
import java.util.UUID

@Composable
fun TaskEventScreen(
    taskId: String? = null,
    navHostController: NavHostController,
) {
    val todoItemsRepository = TodoItemsRepository()
    val viewModel = TaskEventViewModel(toDoItemsRepository = todoItemsRepository)

    var checked by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.formatDateMillisTo("d MMM yyyy")
    var taskText by remember { mutableStateOf("") }
    var importance by remember { mutableStateOf(Importance.NORMAL) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navHostController.popBackStack() }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = AppIcons.ChevronLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surfaceTint,
                        )
                    }
                },
                title = {},
                actions = {
                    TextButton(
                        enabled = taskText.isNotEmpty(),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        onClick = {
                            viewModel.addTask(
                                task = ToDoItem(
                                    id = UUID.randomUUID().toString(),
                                    text = taskText,
                                    importance = importance,
                                    deadline = datePickerState.selectedDateMillis?.convertMillisToDate(),
                                    isCompleted = false,
                                    createdAt = Calendar.getInstance().time,
                                )
                            )
                            navHostController.popBackStack()
                        }
                    ) {
                        Text(
                            text = "Сохранить",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                TextField(
                    value = taskText,
                    onValueChange = { taskText = it },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 4,
                    placeholder = {
                        Text(
                            text = "Что надо сделать?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                )
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Важность",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Importance(
                            importance = importance,
                            onSelectImportance = { importance = it }
                        )
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column {
                                Text(
                                    text = "Сделать до",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                                if (!selectedDate.isNullOrEmpty()) {
                                    VerticalSpacer(height = 2.dp)
                                    Text(
                                        text = selectedDate,
                                        style = MaterialTheme.typography.labelLarge,
                                        color = MaterialTheme.colorScheme.secondary,
                                    )
                                }
                            }
                            Switch(
                                checked = checked,
                                onCheckedChange = { checked = it },
                                thumbContent = {}
                            )
                        }
                        AnimatedContent(targetState = checked, label = "showCalendar") {
                            if (it) {
                                HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                                DatePicker(
                                    modifier = Modifier.fillMaxWidth(),
                                    state = datePickerState,
                                    showModeToggle = false,
                                    title = null,
                                    headline = null,
                                    colors = DatePickerDefaults.colors(
                                        containerColor = Color.Transparent,
                                        navigationContentColor = MaterialTheme.colorScheme.secondary,
                                        todayContentColor = MaterialTheme.colorScheme.secondary,
                                        todayDateBorderColor = MaterialTheme.colorScheme.secondary,
                                        selectedYearContentColor = MaterialTheme.colorScheme.secondary,
                                        selectedDayContainerColor = MaterialTheme.colorScheme.secondary.copy(
                                            alpha = 0.5f
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }
            if (taskId != null) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.error,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(16.dp),
                    onClick = {},
                ) {
                    Text(
                        text = "Удалить",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskEventScreenPreview() {
    ToDoAppTheme {
        TaskEventScreen(navHostController = NavHostController(LocalContext.current))
    }
}