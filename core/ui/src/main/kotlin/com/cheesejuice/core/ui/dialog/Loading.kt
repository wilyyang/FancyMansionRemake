package com.cheesejuice.core.ui.dialog

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cheesejuice.core.ui.theme.n_70
import com.cheesejuice.core.ui.theme.n_75
import com.cheesejuice.core.ui.theme.n_80
import com.cheesejuice.core.ui.theme.n_85
import com.cheesejuice.core.ui.theme.n_90
import com.cheesejuice.core.ui.theme.n_95
import com.cheesejuice.core.ui.theme.white

@Composable
fun Loading(
    onDismiss: () -> Unit = {},
    loadingMessage : String? = null
) {
    Dialog(
        onDismissRequest = onDismiss
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularLoadingAnimation()
            loadingMessage?.let {
                Spacer(Modifier.height(20.dp))
                Text(text = loadingMessage,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.surface)
            }
        }
    }
}

class CircleAnim(val easing : CubicBezierEasing, val radius : Float, val color : Color = white)

@Composable
fun CircularLoadingAnimation() {
    val durationMillis = 1400
    val circleAnims = listOf(
        CircleAnim(easing = CubicBezierEasing(0.44f, 0.0f, 0.9f, 1.0f), radius = 3f, color = n_70),
        CircleAnim(easing = CubicBezierEasing(0.36f, 0.0f, 0.8f, 1.0f), radius = 5f, color = n_75),
        CircleAnim(easing = CubicBezierEasing(0.28f, 0.0f, 0.7f, 1.0f), radius = 7f, color = n_80),
        CircleAnim(easing = CubicBezierEasing(0.2f,  0.0f, 0.6f, 1.0f), radius = 10f, color = n_85),
        CircleAnim(easing = CubicBezierEasing(0.12f, 0.0f, 0.5f, 1.0f), radius = 13f, color = n_90),
        CircleAnim(easing = CubicBezierEasing(0.04f, 0.0f, 0.4f, 1.0f), radius = 16f, color = n_95),
    )

    val animationAngles = circleAnims.map {
        val infiniteTransition = rememberInfiniteTransition()
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis,
                    easing = it.easing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Spacer(modifier = Modifier
        .size(40.dp)
        .drawBehind {
            circleAnims.forEachIndexed { index, circleAnim ->
                val rotationCenter = Offset(size.width / 2, size.height / 2)
                val circleCenter = Offset(size.width / 2, 0f)
                rotate(animationAngles[index].value, pivot = rotationCenter) {
                    drawCircle(
                        color = circleAnim.color,
                        center = circleCenter,
                        radius = circleAnim.radius
                    )
                }
            }
        }
    )
}