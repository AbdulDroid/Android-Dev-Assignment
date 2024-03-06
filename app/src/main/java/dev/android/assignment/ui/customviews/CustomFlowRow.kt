package dev.android.assignment.ui.customviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset

@Composable
fun CustomFlowRow(
    modifier: Modifier = Modifier,
    maxRows: Int = 3,
    onContentOverflow: () -> Unit,
    content: @Composable () -> Unit,
) {
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        var currentRow = 0
        var currentOrigin = IntOffset.Zero
        val placeables = measurables.map { measurable ->
            try {
                val placeable = measurable.measure(constraints)

                if (currentOrigin.x > 0f && currentOrigin.x + placeable.width > constraints.maxWidth) {
                    if (currentRow + 1 > maxRows) {
                        onContentOverflow()
                        return@map null
                    }
                    currentRow += 1
                    currentOrigin =
                        currentOrigin.copy(x = 0, y = currentOrigin.y + placeable.height)
                }
                placeable to currentOrigin.also {
                    currentOrigin = it.copy(x = it.x + placeable.width)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@map null
            }
        }

        layout(
            width = constraints.maxWidth,
            height = placeables.lastOrNull()?.run { first.height + second.y } ?: 0
        ) {
            placeables.filterNotNull().forEach {
                val (placeable, origin) = it
                placeable.place(origin.x, origin.y)
            }
        }
    }
}