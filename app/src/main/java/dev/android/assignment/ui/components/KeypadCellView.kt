package dev.android.assignment.ui.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import dev.android.assignment.ui.theme.AppTheme
import dev.android.assignment.R

@Composable
fun KeypadCellView(
    modifier: Modifier = Modifier, keyData: String, onKeyClick: (data: String?) -> Unit
) {
    val hapticFeedback = LocalHapticFeedback.current
    val keyDelete = stringResource(id = R.string.key_delete)
    val keyClear = stringResource(id = R.string.key_clear)
    val keyClearText = stringResource(id = R.string.key_clear_text)
    when (keyData) {
        keyDelete -> {
            IconAppButton(
                icon = ImageVector.vectorResource(id = R.drawable.ic_backspace),
                modifier = modifier
            ) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onKeyClick(keyData)
            }
        }

        keyClear -> {
            TextAppButton(
                modifier = modifier.then(Modifier.wrapContentSize(Alignment.Center)),
                style = MaterialTheme.typography.displaySmall,
                text = keyClearText,
            ) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onKeyClick(keyData)
            }
        }

        else -> TextAppButton(
            modifier = modifier.then(Modifier.wrapContentSize(Alignment.Center)),
            text = keyData,
        ) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onKeyClick(keyData)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewKeypadCellView() {
    AppTheme { KeypadCellView(keyData = "0") { } }
}