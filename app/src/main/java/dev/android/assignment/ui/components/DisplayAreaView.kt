package dev.android.assignment.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.android.assignment.intent.AppIntent
import dev.android.assignment.intent.AppState
import dev.android.assignment.models.Digit
import dev.android.assignment.ui.customviews.CustomFlowRow
import dev.android.assignment.utils.AnimationUtils.addAnimation
import dev.android.assignment.utils.AnimationUtils.removeAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import dev.android.assignment.R

@Composable
fun ColumnScope.DisplayAreaView(
    modifier: Modifier = Modifier,
    intent: Channel<AppIntent>,
    state: AppState,
    scope: CoroutineScope,
) {
    Box(
        modifier
            .weight(1f)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        val overflowError = stringResource(id = R.string.overflow_error)
        CustomFlowRow(
            modifier = modifier
                .wrapContentSize(Alignment.CenterEnd)
                .animateContentSize(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                ),
            onContentOverflow = {
                scope.launch {
                    intent.send(AppIntent.DeletePressed)
                    intent.send(AppIntent.OverflowError(overflowError))
                }
            }
        ) {

            state.displayData
                .mapIndexed { index, char -> Digit(char, state.displayData, index) }
                .forEachIndexed { index, digit ->
                    if (index == state.displayData.length - 1) {
                        AnimatedContent(
                            targetState = digit,
                            transitionSpec = {
                                if (targetState.fullString.length > initialState.fullString.length) {
                                    addAnimation
                                } else {
                                    removeAnimation
                                }
                            },
                            label = "${digit.digitChar}"
                        ) { currentDigit ->
                            Text(
                                text = "${currentDigit.digitChar}",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    } else {
                        Text(
                            text = "${digit.digitChar}",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                }
        }
    }
}