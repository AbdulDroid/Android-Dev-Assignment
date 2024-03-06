package dev.android.assignment.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import dev.android.assignment.models.Digit

object AnimationUtils {

    val AnimatedContentTransitionScope<Digit>.addAnimation
        get() = fadeIn() + scaleIn(
            initialScale = .5f,
            animationSpec = spring(
                stiffness = Spring.StiffnessLow
            )
        ) + slideInVertically(
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) { -it } togetherWith scaleOut(
            targetScale = .5f
        ) + fadeOut() + slideOutVertically { it } using
                SizeTransform(
                    clip = false,
                    sizeAnimationSpec = { _, _ ->
                        spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow,
                        )
                    }
                )
    val AnimatedContentTransitionScope<Digit>.removeAnimation
        get() = fadeIn() + scaleIn(
            initialScale = -.5f,
            animationSpec = spring(
                stiffness = Spring.StiffnessLow
            )
        ) + slideInVertically(
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ) { it } togetherWith scaleOut(
            targetScale = .5f
        ) + fadeOut() + slideOutVertically { -it } using
                SizeTransform(
                    clip = false,
                    sizeAnimationSpec = { _, _ ->
                        spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow,
                        )
                    }
                )
}