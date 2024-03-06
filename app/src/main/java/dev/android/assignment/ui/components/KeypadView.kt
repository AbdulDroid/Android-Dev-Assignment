package dev.android.assignment.ui.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.touchlane.gridpad.GridPad
import com.touchlane.gridpad.GridPadCells
import dev.android.assignment.extensions.emitKeyPress
import dev.android.assignment.ui.theme.AppTheme
import dev.android.assignment.utils.KeypadUtils

@Composable
internal fun KeyPadView(
    modifier: Modifier = Modifier, keys: List<String>, onKeyClicked: (String?) -> Unit = { _ -> },
) {
    val view = LocalView.current
    GridPad(
        modifier = modifier.fillMaxSize(),
        cells = GridPadCells(columnCount = 3, rowCount = 4),
    ) {
        keys.forEach { data ->
            item {
                KeypadCellView(
                    keyData = data,
                    onKeyClick = { key ->
                        key?.let { view.emitKeyPress(it) }
                        onKeyClicked(key)
                    },
                )
            }
        }
    }
}

private const val COLOR = Color.WHITE.toLong()

@Preview(showBackground = true, heightDp = 480, widthDp = 320, backgroundColor = COLOR)
@Preview(showBackground = true, heightDp = 320, widthDp = 240, backgroundColor = COLOR)
@Preview(
    name = "NEXUS_5",
    showSystemUi = true,
    device = Devices.NEXUS_5,
    showBackground = true,
    backgroundColor = COLOR
)
@Preview(
    name = "PIXEL_2",
    showSystemUi = true,
    device = Devices.PIXEL_2,
    showBackground = true,
    backgroundColor = COLOR
)
@Preview(
    name = "NEXUS_7",
    showSystemUi = true,
    device = Devices.NEXUS_7,
    showBackground = true,
    backgroundColor = COLOR
)
@Composable
private fun PreviewKeyPadView() {
    AppTheme {
        KeyPadView(keys = KeypadUtils.getKeys())
    }
}