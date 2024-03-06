package dev.android.assignment.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextAppButton(
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayLarge,
    text: String,
    onClick: () -> Unit
) {
    AppButton(onClick = onClick, modifier = modifier) {
        Text(text = text, style = style)
    }
}

@Composable
fun IconAppButton(modifier: Modifier = Modifier, icon: ImageVector, onClick: () -> Unit) {
    AppButton(onClick = onClick, modifier = modifier) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(48.dp))
    }
}

@Composable
fun AppButton(
    onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val corner: Dp by animateDpAsState(
        if (isPressed) {
            30.dp
        } else {
            20.dp
        },
        tween(150, easing = LinearOutSlowInEasing), label = ""
    )
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        shape = RoundedCornerShape(corner),
        contentPadding = PaddingValues(0.dp),
        interactionSource = interactionSource
    ) { content() }
}