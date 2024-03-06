package dev.android.assignment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.android.assignment.intent.AppIntent
import dev.android.assignment.intent.AppState
import dev.android.assignment.ui.components.DisplayAreaView
import dev.android.assignment.ui.components.KeyPadView
import dev.android.assignment.utils.KeypadUtils
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Composable
fun KeypadScreen(
    modifier: Modifier = Modifier, intent: Channel<AppIntent>, state: AppState,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold { padding ->
        if (state.error != null) {
            Toast.makeText(context, state.error.message, Toast.LENGTH_SHORT).show()
        }
        Column(
            modifier = Modifier.padding(padding)
        ) {
            DisplayAreaView(
                modifier = modifier,
                intent = intent,
                state = state,
                scope = coroutineScope,
            )
            KeyPadView(
                modifier = Modifier
                    .weight(2f)
                    .align(Alignment.End),
                keys = KeypadUtils.getKeys(),
                onKeyClicked = { key ->
                    coroutineScope.launch {
                        key?.let {
                            when (it) {
                                KeypadUtils.SpecialKeys.Delete.name -> {
                                    intent.send(AppIntent.DeletePressed)
                                }

                                KeypadUtils.SpecialKeys.Clear.name -> {
                                    intent.send(AppIntent.ClearPressed)
                                }

                                else -> {
                                    intent.send(AppIntent.KeyPressed(key))
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}