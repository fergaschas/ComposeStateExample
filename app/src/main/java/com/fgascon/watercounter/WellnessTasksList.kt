package com.fgascon.watercounter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable


@Composable
fun WellnessTasksList(
    wellnessTasks: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onClose: (WellnessTask) -> Unit
) {
    LazyColumn {
        items(wellnessTasks) { task ->
            WellnessTaskItem(
                taskName = task.label,
                onClose = { onClose(task) },
                taskChecked = task.checked,
                onTaskChecked = { checked ->
                    onCheckedTask(task, checked)
                }
            )
        }
    }
}