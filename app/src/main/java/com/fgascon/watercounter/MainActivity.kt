package com.fgascon.watercounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fgascon.watercounter.ui.theme.WaterCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterCounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        WellnessScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun WellnessScreen(
    wellnessViewModel: WellnessViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val count: MutableState<Int> = remember { mutableStateOf(0) }
    WellnessTasksList(
        wellnessTasks = wellnessViewModel.tasks,
        onCheckedTask = { task, check -> wellnessViewModel.changeTaskChecked(task, check) },
        onClose = { task -> wellnessViewModel.remove(task) }
    )
}

@Composable
fun WaterCounter(
    count: Int,
    onIncrementCount: () -> Unit,
    onClearCount: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by rememberSaveable { mutableStateOf(true) }
            if (showTask) {
               /* WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = { showTask = false },
                    onTaskChecked =
                )*/
            }
            Text(text = "You've had $count glasses.")
        }
        Row {
            Button(
                onClick = onIncrementCount,
                enabled = count < 10,
            ) {
                Text(text = "Add one")
            }
            Button(
                onClick = onClearCount,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Clear water count")
            }
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    taskChecked : Boolean,
    onTaskChecked : (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = taskName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = taskChecked, onCheckedChange = onTaskChecked)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

