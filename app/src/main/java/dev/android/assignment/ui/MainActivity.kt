package dev.android.assignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.android.assignment.intent.AppState
import dev.android.assignment.ui.screens.KeypadScreen
import dev.android.assignment.ui.theme.AppTheme
import kotlinx.coroutines.channels.Channel

class MainActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.collectAsStateWithLifecycle()
            val intent = viewModel.intent
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KeypadScreen(
                        intent = intent,
                        state = state.value
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        KeypadScreen(
            intent = Channel(),
            state = AppState(displayData = "")
        )
    }
}