package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PositionMannequinCanvas(
    positionId: String,
    highlightStep: Int? = null,
    modifier: Modifier = Modifier
) {
    // Beautiful, distinct, and contrasting colors for Partner A and Partner B to prevent confusion!
    val colorA = Color(0xFFFF5D73) // Warm Rose Coral
    val colorB = Color(0xFF00B4D8) // Radiant Ocean Teal
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface
    val gridColor = onSurfaceColor.copy(alpha = 0.05f)

    // Smooth continuous breathing/pulsing animation representing dynamic rhythm and life
    val infiniteTransition = rememberInfiniteTransition(label = "breathe")
    val breathFactor by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breath"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.15f))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val w = size.width
            val h = size.height

            // Calculate dynamic breathing offsets in pixels
            val maxOffset = 3.5.dp.toPx()
            val aOffsetPulse = breathFactor * maxOffset
            val bOffsetPulse = (1f - breathFactor) * maxOffset // Slightly out of phase for natural interaction

            // 1. Draw elegant background grid lines for a professional blueprint/technical-drawing feel
            val spacingX = w / 8
            for (i in 1..7) {
                drawLine(
                    color = gridColor,
                    start = Offset(spacingX * i, 0f),
                    end = Offset(spacingX * i, h),
                    strokeWidth = 1.dp.toPx()
                )
            }
            val spacingY = h / 6
            for (i in 1..5) {
                drawLine(
                    color = gridColor,
                    start = Offset(0f, spacingY * i),
                    end = Offset(w, spacingY * i),
                    strokeWidth = 1.dp.toPx()
                )
            }

            // Draw a grounding line at the bottom
            drawLine(
                color = gridColor.copy(alpha = 0.12f),
                start = Offset(w * 0.1f, h * 0.85f),
                end = Offset(w * 0.9f, h * 0.85f),
                strokeWidth = 2.dp.toPx()
            )

            // Define stroke parameters
            val cap = StrokeCap.Round
            val join = StrokeJoin.Round

            // Helper to determine if a specific part of a person should be highlighted
            fun isHighlighted(part: String): Boolean {
                if (highlightStep == null) return false
                return when (positionId) {
                    "misionero" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_head" || part == "A_legs" || part == "A_arms"
                            2 -> part == "B_body" || part == "B_head" || part == "B_legs" || part == "B_arms"
                            3 -> part == "A_head" || part == "B_head" || part == "gaze"
                            4 -> part == "pelvis" || part == "motion"
                            else -> false
                        }
                    }
                    "cuchara" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "B_body" || part == "A_head" || part == "B_head"
                            2 -> part == "B_arms" || part == "B_body"
                            3 -> part == "A_legs" || part == "A_body"
                            4 -> part == "motion" || part == "pelvis" || part == "rhythm"
                            else -> false
                        }
                    }
                    "loto" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_legs" || part == "A_head"
                            2 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            3 -> part == "A_arms" || part == "B_arms" || part == "A_head" || part == "B_head"
                            4 -> part == "pelvis" || part == "motion"
                            else -> false
                        }
                    }
                    "arco" -> {
                        when (highlightStep) {
                            1 -> part == "A_legs" || part == "A_body"
                            2 -> part == "A_body" || part == "pelvis" || part == "motion"
                            3 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            4 -> part == "motion" || part == "pelvis"
                            else -> false
                        }
                    }
                    "mariposa" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_head"
                            2 -> part == "A_legs"
                            3 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            4 -> part == "B_arms" || part == "A_legs" || part == "motion"
                            else -> false
                        }
                    }
                    "puente_oro" -> {
                        when (highlightStep) {
                            1 -> part == "B_body" || part == "B_legs" || part == "A_legs"
                            2 -> part == "A_body" || part == "A_head"
                            3 -> part == "A_body" || part == "pelvis" || part == "motion"
                            4 -> part == "motion" || part == "pelvis"
                            else -> false
                        }
                    }
                    "estrella" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_legs" || part == "A_arms" || part == "A_head"
                            2 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            3 -> part == "A_legs" || part == "B_legs"
                            4 -> part == "motion" || part == "A_body" || part == "B_body"
                            else -> false
                        }
                    }
                    "gondola" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "B_body" || part == "A_legs" || part == "B_legs" || part == "A_head" || part == "B_head"
                            2 -> part == "B_legs" || part == "A_legs"
                            3 -> part == "A_arms" || part == "B_arms"
                            4 -> part == "motion" || part == "A_body" || part == "B_body"
                            else -> false
                        }
                    }
                    "balanza" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_legs" || part == "A_head"
                            2 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            3 -> part == "A_arms" || part == "B_legs" || part == "B_arms"
                            4 -> part == "motion" || part == "A_legs"
                            else -> false
                        }
                    }
                    "triangulo" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_arms" || part == "A_head"
                            2 -> part == "A_legs"
                            3 -> part == "B_body" || part == "B_arms" || part == "B_head"
                            4 -> part == "motion" || part == "pelvis"
                            else -> false
                        }
                    }
                    "venus" -> {
                        when (highlightStep) {
                            1 -> part == "A_legs" || part == "B_legs" || part == "A_body" || part == "B_body"
                            2 -> part == "A_body" || part == "B_body" || part == "A_head" || part == "B_head"
                            3 -> part == "A_arms" || part == "B_arms" || part == "A_head" || part == "B_head"
                            4 -> part == "pelvis" || part == "motion" || part == "rhythm"
                            else -> false
                        }
                    }
                    "mariposa_lesbica" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_legs"
                            2 -> part == "B_body" || part == "B_legs" || part == "B_head"
                            3 -> part == "A_arms" || part == "B_arms"
                            4 -> part == "pelvis" || part == "motion"
                            else -> false
                        }
                    }
                    "arco_anal" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "A_legs"
                            2 -> part == "A_legs" || part == "pelvis"
                            3 -> part == "B_body" || part == "B_legs" || part == "B_arms"
                            4 -> part == "motion" || part == "pelvis"
                            else -> false
                        }
                    }
                    "cuchara_anal" -> {
                        when (highlightStep) {
                            1 -> part == "A_body" || part == "B_body"
                            2 -> part == "A_legs" || part == "A_body"
                            3 -> part == "A_body" || part == "B_body" || part == "pelvis"
                            4 -> part == "motion" || part == "pelvis"
                            else -> false
                        }
                    }
                    else -> false
                }
            }

            // Volumetric Drawing Helpers to make mannequins look "más humano" and highly artistic
            fun drawVolumetricHead(cx: Float, cy: Float, radius: Float, color: Color, isHighlighted: Boolean) {
                // Highlighted items glow with a radiant coral pink/rose color
                val baseColor = if (isHighlighted) Color(0xFFFF4D6D) else color
                val alphaFlesh = if (isHighlighted) 0.38f else 0.18f
                val strokeWidth = if (isHighlighted) 4.5.dp.toPx() else 3.dp.toPx()

                // Flesh volume (soft filled circle for anatomical mass)
                drawCircle(
                    color = baseColor.copy(alpha = alphaFlesh),
                    center = Offset(cx, cy),
                    radius = radius + 3.dp.toPx()
                )
                // Core anatomical outline
                drawCircle(
                    color = baseColor,
                    center = Offset(cx, cy),
                    radius = radius,
                    style = Stroke(width = strokeWidth)
                )
                // Small inner core focal point
                drawCircle(
                    color = baseColor.copy(alpha = 0.5f),
                    center = Offset(cx, cy),
                    radius = radius * 0.35f
                )
            }

            fun drawVolumetricSegment(
                x1: Float, y1: Float,
                x2: Float, y2: Float,
                color: Color,
                isHighlighted: Boolean,
                isTorso: Boolean = false
            ) {
                val baseColor = if (isHighlighted) Color(0xFFFF4D6D) else color
                val fleshAlpha = if (isHighlighted) 0.38f else 0.18f
                val coreWidth = if (isHighlighted) 5.dp.toPx() else 3.dp.toPx()
                
                // Real anatomical thickness (torso is thicker than limbs)
                val fleshWidth = if (isTorso) {
                    if (isHighlighted) 30.dp.toPx() else 24.dp.toPx()
                } else {
                    if (isHighlighted) 18.dp.toPx() else 13.dp.toPx()
                }

                // 1. Outer volumetric flesh representing real body silhouette
                drawLine(
                    color = baseColor.copy(alpha = fleshAlpha),
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    strokeWidth = fleshWidth,
                    cap = StrokeCap.Round
                )
                
                // 2. Inner skeletal core representing postural alignment
                drawLine(
                    color = baseColor,
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    strokeWidth = coreWidth,
                    cap = StrokeCap.Round
                )
            }

            fun drawVolumetricThreeJoint(
                x1: Float, y1: Float,
                x2: Float, y2: Float,
                x3: Float, y3: Float,
                color: Color,
                isHighlighted: Boolean
            ) {
                drawVolumetricSegment(x1, y1, x2, y2, color, isHighlighted, isTorso = false)
                drawVolumetricSegment(x2, y2, x3, y3, color, isHighlighted, isTorso = false)
                
                // Anatomical joint node
                val baseColor = if (isHighlighted) Color(0xFFFF4D6D) else color
                drawCircle(
                    color = baseColor,
                    center = Offset(x2, y2),
                    radius = 3.5.dp.toPx()
                )
            }

            // Bridge standard single-line helpers to modern volumetric drawings with dynamic breathing/flexing motion
            fun drawHead(cx: Float, cy: Float, radius: Float, color: Color) {
                val isA = (color == colorA)
                val partName = if (isA) "A_head" else "B_head"
                
                // Sway gently left-right and lift up-down for chest breathing
                val dx = if (isA) aOffsetPulse * 0.4f else bOffsetPulse * 0.4f
                val dy = if (isA) -aOffsetPulse else -bOffsetPulse
                
                drawVolumetricHead(cx + dx, cy + dy, radius, color, isHighlighted(partName))
            }

            fun drawSegment(x1: Float, y1: Float, x2: Float, y2: Float, color: Color) {
                val isA = (color == colorA)
                val partName = if (isA) "A_body" else "B_body"
                
                val dx1 = if (isA) aOffsetPulse * 0.3f else bOffsetPulse * 0.3f
                val dy1 = if (isA) -aOffsetPulse * 0.8f else -bOffsetPulse * 0.8f
                val dx2 = if (isA) aOffsetPulse * 0.3f else bOffsetPulse * 0.3f
                val dy2 = if (isA) -aOffsetPulse * 0.8f else -bOffsetPulse * 0.8f
                
                val len = kotlin.math.hypot(x2 - x1, y2 - y1)
                
                // Torso heuristic: spine segment is longer than limb parts
                val isTorso = len > h * 0.18f
                
                drawVolumetricSegment(
                    x1 + dx1, y1 + dy1,
                    x2 + dx2, y2 + dy2,
                    color,
                    isHighlighted(partName),
                    isTorso
                )
            }

            fun drawThreeJoint(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, color: Color) {
                val isA = (color == colorA)
                val partName = if (isA) "A_legs" else "B_legs"
                
                // Keep feet (x3, y3) grounded to the bed/floor, while knees (x2, y2) and hips (x1, y1) flex rhythmically!
                val dx1 = if (isA) aOffsetPulse * 0.3f else bOffsetPulse * 0.3f
                val dy1 = if (isA) -aOffsetPulse * 0.8f else -bOffsetPulse * 0.8f
                val dx2 = if (isA) aOffsetPulse * 0.15f else bOffsetPulse * 0.15f
                val dy2 = if (isA) -aOffsetPulse * 0.4f else -bOffsetPulse * 0.4f
                val dx3 = 0f
                val dy3 = 0f
                
                drawVolumetricThreeJoint(
                    x1 + dx1, y1 + dy1,
                    x2 + dx2, y2 + dy2,
                    x3 + dx3, y3 + dy3,
                    color,
                    isHighlighted(partName)
                )
            }

            // Coordinate translations based on current canvas dimension
            when (positionId) {
                "misionero" -> {
                    // Person A (Lying down - Primary)
                    val headX = w * 0.28f
                    val headY = h * 0.72f
                    val headRad = w * 0.055f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.35f
                    val shY = h * 0.75f
                    val hipX = w * 0.62f
                    val hipY = h * 0.75f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Legs
                    drawThreeJoint(hipX, hipY, w * 0.74f, h * 0.63f, w * 0.84f, h * 0.73f, colorA)
                    // Arms
                    drawSegment(shX, shY, w * 0.48f, h * 0.75f, colorA)

                    // Person B (On top - Secondary)
                    val bHeadX = w * 0.33f
                    val bHeadY = h * 0.53f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.38f
                    val bShY = h * 0.57f
                    val bHipX = w * 0.60f
                    val bHipY = h * 0.66f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine

                    // Legs
                    drawThreeJoint(bHipX, bHipY, w * 0.71f, h * 0.73f, w * 0.82f, h * 0.78f, colorB)
                    // Arms supporting
                    drawThreeJoint(bShX, bShY, w * 0.35f, h * 0.67f, w * 0.38f, h * 0.75f, colorB)
                }

                "cuchara" -> {
                    // Person A (Front - Primary)
                    val headRad = w * 0.055f
                    val headX = w * 0.46f
                    val headY = h * 0.54f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.46f
                    val shY = h * 0.60f
                    val hipX = w * 0.64f
                    val hipY = h * 0.64f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Legs bent
                    drawThreeJoint(hipX, hipY, w * 0.54f, h * 0.74f, w * 0.66f, h * 0.81f, colorA)
                    // Arms
                    drawSegment(shX, shY, w * 0.38f, h * 0.64f, colorA)

                    // Person B (Back - Secondary)
                    val bHeadX = w * 0.32f
                    val bHeadY = h * 0.52f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.33f
                    val bShY = h * 0.58f
                    val bHipX = w * 0.51f
                    val bHipY = h * 0.62f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine

                    // Legs bent
                    drawThreeJoint(bHipX, bHipY, w * 0.41f, h * 0.72f, w * 0.54f, h * 0.79f, colorB)
                    // Arm wrapping around
                    drawThreeJoint(bShX, bShY, w * 0.44f, h * 0.59f, w * 0.50f, h * 0.63f, colorB)
                }

                "loto" -> {
                    // Person A (Sitting cross-legged - Primary)
                    val headRad = w * 0.055f
                    val headX = w * 0.50f
                    val headY = h * 0.41f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.50f
                    val shY = h * 0.47f
                    val hipX = w * 0.50f
                    val hipY = h * 0.73f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Left/Right Legs folded
                    drawThreeJoint(hipX, hipY, w * 0.35f, h * 0.76f, w * 0.48f, h * 0.80f, colorA)
                    drawThreeJoint(hipX, hipY, w * 0.65f, h * 0.76f, w * 0.52f, h * 0.80f, colorA)
                    // Arms wrapping back
                    drawSegment(shX, shY, w * 0.42f, h * 0.58f, colorA)

                    // Person B (Sitting in lap - Secondary)
                    val bHeadX = w * 0.44f
                    val bHeadY = h * 0.36f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.44f
                    val bShY = h * 0.42f
                    val bHipX = w * 0.47f
                    val bHipY = h * 0.70f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine

                    // Legs wrapping A's hips
                    drawThreeJoint(bHipX, bHipY, w * 0.33f, h * 0.63f, w * 0.56f, h * 0.66f, colorB)
                    // Arms around neck
                    drawThreeJoint(bShX, bShY, w * 0.54f, h * 0.44f, w * 0.49f, h * 0.54f, colorB)
                }

                "arco" -> {
                    val headRad = w * 0.055f
                    // Person A (Arched - Primary)
                    val headX = w * 0.26f
                    val headY = h * 0.72f
                    drawHead(headX, headY, headRad, colorA)

                    // Curved spinal arch (approximated with connected lines)
                    val shX = w * 0.32f
                    val shY = h * 0.68f
                    val midX = w * 0.48f
                    val midY = h * 0.50f
                    val hipX = w * 0.66f
                    val hipY = h * 0.68f

                    drawSegment(shX, shY, midX, midY, colorA)
                    drawSegment(midX, midY, hipX, hipY, colorA)

                    // Legs bent
                    drawThreeJoint(hipX, hipY, w * 0.77f, h * 0.56f, w * 0.82f, h * 0.74f, colorA)
                    // Arm holding floor
                    drawSegment(shX, shY, w * 0.36f, h * 0.74f, colorA)

                    // Person B (Kneeling - Secondary)
                    val bHeadX = w * 0.48f
                    val bHeadY = h * 0.41f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.48f
                    val bShY = h * 0.47f
                    val bHipX = w * 0.52f
                    val bHipY = h * 0.66f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB)

                    // Legs kneeling
                    drawThreeJoint(bHipX, bHipY, w * 0.43f, h * 0.75f, w * 0.56f, h * 0.75f, colorB)
                    // Arm
                    drawSegment(bShX, bShY, w * 0.44f, h * 0.58f, colorB)
                }

                "mariposa" -> {
                    val headRad = w * 0.055f
                    // Bed line
                    drawLine(
                        color = onSurfaceColor.copy(alpha = 0.15f),
                        start = Offset(w * 0.1f, h * 0.72f),
                        end = Offset(w * 0.62f, h * 0.72f),
                        strokeWidth = 3.dp.toPx()
                    )

                    // Person A (Lying down on bed edge - Primary)
                    val headX = w * 0.21f
                    val headY = h * 0.64f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.25f
                    val shY = h * 0.67f
                    val hipX = w * 0.51f
                    val hipY = h * 0.67f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Wings legs bent wide
                    drawThreeJoint(hipX, hipY, w * 0.42f, h * 0.53f, w * 0.50f, h * 0.45f, colorA)
                    // Arm
                    drawSegment(shX, shY, w * 0.32f, h * 0.67f, colorA)

                    // Person B (Standing/Kneeling outside bed - Secondary)
                    val bHeadX = w * 0.67f
                    val bHeadY = h * 0.44f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.67f
                    val bShY = h * 0.49f
                    val bHipX = w * 0.65f
                    val bHipY = h * 0.72f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine

                    // Legs kneeling
                    drawThreeJoint(bHipX, bHipY, w * 0.60f, h * 0.81f, w * 0.69f, h * 0.81f, colorB)
                    // Arm holding A's legs
                    drawSegment(bShX, bShY, w * 0.51f, h * 0.52f, colorB)
                }

                "puente_oro" -> {
                    val headRad = w * 0.055f
                    // Person A (High bridge - Primary)
                    val headX = w * 0.20f
                    val headY = h * 0.72f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.24f
                    val shY = h * 0.72f
                    val hipX = w * 0.46f
                    val hipY = h * 0.50f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Torso angled up

                    // Legs lifted
                    drawThreeJoint(hipX, hipY, w * 0.60f, h * 0.40f, w * 0.65f, h * 0.40f, colorA)
                    // Supporting arm
                    drawSegment(shX, shY, w * 0.32f, h * 0.72f, colorA)

                    // Person B (Kneeling holding legs - Secondary)
                    val bHeadX = w * 0.73f
                    val bHeadY = h * 0.42f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.73f
                    val bShY = h * 0.47f
                    val bHipX = w * 0.75f
                    val bHipY = h * 0.68f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB)

                    // Legs
                    drawThreeJoint(bHipX, bHipY, w * 0.64f, h * 0.75f, w * 0.78f, h * 0.75f, colorB)
                    // Arm holding ankles
                    drawSegment(bShX, bShY, w * 0.63f, h * 0.41f, colorB)
                }

                "estrella" -> {
                    val headRad = w * 0.055f
                    // Person A (Lying open star - Primary)
                    val headX = w * 0.31f
                    val headY = h * 0.66f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.34f
                    val shY = h * 0.68f
                    val hipX = w * 0.57f
                    val hipY = h * 0.68f
                    drawSegment(shX, shY, hipX, hipY, colorA)

                    // Legs spread wide
                    drawSegment(hipX, hipY, w * 0.74f, h * 0.60f, colorA)
                    drawSegment(hipX, hipY, w * 0.74f, h * 0.76f, colorA)
                    // Arms spread
                    drawSegment(shX, shY, w * 0.20f, h * 0.58f, colorA)
                    drawSegment(shX, shY, w * 0.20f, h * 0.78f, colorA)

                    // Person B (Diagonal above - Secondary)
                    val bHeadX = w * 0.41f
                    val bHeadY = h * 0.49f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.43f
                    val bShY = h * 0.54f
                    val bHipX = w * 0.62f
                    val bHipY = h * 0.64f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB)

                    // Legs
                    drawThreeJoint(bHipX, bHipY, w * 0.72f, h * 0.71f, w * 0.76f, h * 0.71f, colorB)
                    // Arm
                    drawSegment(bShX, bShY, w * 0.38f, h * 0.64f, colorB)
                }

                "gondola" -> {
                    val headRad = w * 0.055f
                    // Person A (Leaning back sitting - Primary)
                    val headX = w * 0.36f
                    val headY = h * 0.43f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.36f
                    val shY = h * 0.48f
                    val hipX = w * 0.30f
                    val hipY = h * 0.73f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine leaning back

                    // Legs bent
                    drawThreeJoint(hipX, hipY, w * 0.45f, h * 0.78f, w * 0.57f, h * 0.70f, colorA)
                    // Arm holding hands
                    drawSegment(shX, shY, w * 0.45f, h * 0.51f, colorA)

                    // Person B (Facing A sitting - Secondary)
                    val bHeadX = w * 0.64f
                    val bHeadY = h * 0.43f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.64f
                    val bShY = h * 0.48f
                    val bHipX = w * 0.70f
                    val bHipY = h * 0.73f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB)

                    // Legs bent inside
                    drawThreeJoint(bHipX, bHipY, w * 0.55f, h * 0.78f, w * 0.43f, h * 0.70f, colorB)
                    // Arm holding hands
                    drawSegment(bShX, bShY, w * 0.55f, h * 0.51f, colorB)
                }

                "balanza" -> {
                    val headRad = w * 0.055f
                    // Person A (Standing carrying B - Primary)
                    val headX = w * 0.50f
                    val headY = h * 0.30f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.50f
                    val shY = h * 0.35f
                    val hipX = w * 0.50f
                    val hipY = h * 0.64f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Standing Legs
                    drawThreeJoint(hipX, hipY, w * 0.41f, h * 0.73f, w * 0.42f, h * 0.82f, colorA)
                    drawThreeJoint(hipX, hipY, w * 0.59f, h * 0.73f, w * 0.58f, h * 0.82f, colorA)
                    // Arms holding B's hips
                    drawSegment(shX, shY, w * 0.44f, h * 0.52f, colorA)

                    // Person B (Suspended in air - Secondary)
                    val bHeadX = w * 0.41f
                    val bHeadY = h * 0.36f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.41f
                    val bShY = h * 0.41f
                    val bHipX = w * 0.44f
                    val bHipY = h * 0.60f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Torso hanging

                    // Wrapping legs
                    drawThreeJoint(bHipX, bHipY, w * 0.58f, h * 0.52f, w * 0.46f, h * 0.46f, colorB)
                    // Arms wrapping neck
                    drawSegment(bShX, bShY, w * 0.50f, h * 0.34f, colorB)
                }

                "triangulo" -> {
                    val headRad = w * 0.055f
                    // Support table line
                    drawLine(
                        color = onSurfaceColor.copy(alpha = 0.15f),
                        start = Offset(w * 0.15f, h * 0.68f),
                        end = Offset(w * 0.34f, h * 0.68f),
                        strokeWidth = 3.dp.toPx()
                    )
                    drawLine(
                        color = onSurfaceColor.copy(alpha = 0.10f),
                        start = Offset(w * 0.28f, h * 0.68f),
                        end = Offset(w * 0.28f, h * 0.85f),
                        strokeWidth = 2.dp.toPx()
                    )

                    // Person A (Bent forward - Primary)
                    val headX = w * 0.31f
                    val headY = h * 0.52f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.34f
                    val shY = h * 0.55f
                    val hipX = w * 0.57f
                    val hipY = h * 0.55f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Flat spine

                    // Legs
                    drawThreeJoint(hipX, hipY, w * 0.62f, h * 0.70f, w * 0.63f, h * 0.82f, colorA)
                    // Supporting Arms
                    drawSegment(shX, shY, w * 0.28f, h * 0.68f, colorA)

                    // Person B (Standing behind - Secondary)
                    val bHeadX = w * 0.72f
                    val bHeadY = h * 0.41f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.72f
                    val bShY = h * 0.46f
                    val bHipX = w * 0.70f
                    val bHipY = h * 0.74f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Upright Spine

                    // Legs standing
                    drawThreeJoint(bHipX, bHipY, w * 0.71f, h * 0.79f, w * 0.72f, h * 0.82f, colorB)
                    // Arms holding A's hips
                    drawSegment(bShX, bShY, w * 0.57f, h * 0.55f, colorB)
                }

                "venus" -> {
                    val headRad = w * 0.055f
                    // Partner A (lying on side facing right)
                    val headX = w * 0.35f
                    val headY = h * 0.55f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.35f
                    val shY = h * 0.61f
                    val hipX = w * 0.53f
                    val hipY = h * 0.63f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Legs entrained slightly forward
                    drawThreeJoint(hipX, hipY, w * 0.44f, h * 0.76f, w * 0.58f, h * 0.81f, colorA)
                    // Arms wrapping/cared
                    drawSegment(shX, shY, w * 0.45f, h * 0.60f, colorA)

                    // Partner B (lying on side facing left)
                    val bHeadX = w * 0.55f
                    val bHeadY = h * 0.55f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.55f
                    val bShY = h * 0.61f
                    val bHipX = w * 0.37f
                    val bHipY = h * 0.63f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine

                    // Legs entrained forward
                    drawThreeJoint(bHipX, bHipY, w * 0.46f, h * 0.76f, w * 0.32f, h * 0.81f, colorB)
                    // Arms clasping/holding
                    drawSegment(bShX, bShY, w * 0.45f, h * 0.60f, colorB)
                }

                "mariposa_lesbica" -> {
                    val headRad = w * 0.055f
                    // Partner A (lying on back, knees open)
                    val headX = w * 0.23f
                    val headY = h * 0.65f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.29f
                    val shY = h * 0.67f
                    val hipX = w * 0.51f
                    val hipY = h * 0.67f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Flat Spine

                    // Wings legs bent upwards/open
                    drawThreeJoint(hipX, hipY, w * 0.45f, h * 0.52f, w * 0.50f, h * 0.44f, colorA)
                    // Arm reaching up
                    drawSegment(shX, shY, w * 0.35f, h * 0.60f, colorA)

                    // Partner B (on top, perpendicularly)
                    val bHeadX = w * 0.53f
                    val bHeadY = h * 0.41f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.53f
                    val bShY = h * 0.47f
                    val bHipX = w * 0.51f
                    val bHipY = h * 0.68f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Vertical spine

                    // Legs kneeling/supporting
                    drawThreeJoint(bHipX, bHipY, w * 0.63f, h * 0.77f, w * 0.73f, h * 0.77f, colorB)
                    // Arm holding A's torso
                    drawSegment(bShX, bShY, w * 0.39f, h * 0.54f, colorB)
                }

                "arco_anal" -> {
                    val headRad = w * 0.055f
                    // Partner A (kneeling doggy-style)
                    val headX = w * 0.28f
                    val headY = h * 0.65f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.36f
                    val shY = h * 0.61f
                    val hipX = w * 0.60f
                    val hipY = h * 0.50f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine sloping up

                    // Kneeling Legs
                    drawThreeJoint(hipX, hipY, w * 0.56f, h * 0.77f, w * 0.45f, h * 0.77f, colorA)
                    // Supporting front arms
                    drawSegment(shX, shY, w * 0.32f, h * 0.75f, colorA)

                    // Partner B (kneeling behind, active)
                    val bHeadX = w * 0.74f
                    val bHeadY = h * 0.35f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.74f
                    val bShY = h * 0.41f
                    val bHipX = w * 0.77f
                    val bHipY = h * 0.63f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Upright Spine

                    // Kneeling legs
                    drawThreeJoint(bHipX, bHipY, w * 0.68f, h * 0.77f, w * 0.79f, h * 0.77f, colorB)
                    // Arm reaching forward to A's hip
                    drawSegment(bShX, bShY, w * 0.61f, h * 0.53f, colorB)
                }

                "cuchara_anal" -> {
                    val headRad = w * 0.055f
                    // Partner A (Front, receiving)
                    val headX = w * 0.47f
                    val headY = h * 0.53f
                    drawHead(headX, headY, headRad, colorA)

                    val shX = w * 0.47f
                    val shY = h * 0.59f
                    val hipX = w * 0.65f
                    val hipY = h * 0.63f
                    drawSegment(shX, shY, hipX, hipY, colorA) // Spine

                    // Legs bent forward
                    drawThreeJoint(hipX, hipY, w * 0.55f, h * 0.73f, w * 0.67f, h * 0.80f, colorA)
                    // Arm supporting
                    drawSegment(shX, shY, w * 0.39f, h * 0.63f, colorA)

                    // Partner B (Back, active)
                    val bHeadX = w * 0.31f
                    val bHeadY = h * 0.51f
                    drawHead(bHeadX, bHeadY, headRad, colorB)

                    val bShX = w * 0.32f
                    val bShY = h * 0.57f
                    val bHipX = w * 0.50f
                    val bHipY = h * 0.61f
                    drawSegment(bShX, bShY, bHipX, bHipY, colorB) // Spine behind

                    // Legs bent behind
                    drawThreeJoint(bHipX, bHipY, w * 0.40f, h * 0.71f, w * 0.53f, h * 0.78f, colorB)
                    // Arm holding A's torso
                    drawSegment(bShX, bShY, w * 0.43f, h * 0.58f, colorB)
                }

                else -> {
                    // Fallback placeholder geometric art
                    drawCircle(
                        color = colorA,
                        center = Offset(w * 0.4f, h * 0.5f),
                        radius = w * 0.12f,
                        style = Stroke(width = 4.dp.toPx())
                    )
                    drawCircle(
                        color = colorB,
                        center = Offset(w * 0.6f, h * 0.5f),
                        radius = w * 0.12f,
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }

            // Draw Step-Specific Decorative Indicators (Gaze lines, Pelvis ripples)
            if (highlightStep != null) {
                // Draw elegant gaze lines representing deep visual connection
                if (isHighlighted("gaze")) {
                    val headOffsetPair = when (positionId) {
                        "misionero" -> Offset(w * 0.28f, h * 0.72f) to Offset(w * 0.33f, h * 0.53f)
                        "loto" -> Offset(w * 0.50f, h * 0.41f) to Offset(w * 0.44f, h * 0.36f)
                        "gondola" -> Offset(w * 0.36f, h * 0.43f) to Offset(w * 0.64f, h * 0.43f)
                        else -> null
                    }
                    headOffsetPair?.let { (p1, p2) ->
                        drawLine(
                            color = Color(0xFFFF4D6D),
                            start = p1,
                            end = p2,
                            strokeWidth = 2.dp.toPx(),
                            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
                        )
                    }
                }

                // Draw pulsing pelvic energy ripples representing motion, rhythm, or core alignment
                if (isHighlighted("motion") || isHighlighted("pelvis") || isHighlighted("rhythm")) {
                    val hipCenter = when (positionId) {
                        "misionero" -> Offset(w * 0.61f, h * 0.70f)
                        "cuchara" -> Offset(w * 0.57f, h * 0.63f)
                        "loto" -> Offset(w * 0.48f, h * 0.71f)
                        "arco" -> Offset(w * 0.66f, h * 0.68f)
                        "mariposa" -> Offset(w * 0.51f, h * 0.67f)
                        "puente_oro" -> Offset(w * 0.46f, h * 0.50f)
                        "estrella" -> Offset(w * 0.59f, h * 0.66f)
                        "gondola" -> Offset(w * 0.50f, h * 0.73f)
                        "balanza" -> Offset(w * 0.47f, h * 0.62f)
                        "triangulo" -> Offset(w * 0.63f, h * 0.64f)
                        "venus" -> Offset(w * 0.45f, h * 0.63f)
                        "mariposa_lesbica" -> Offset(w * 0.51f, h * 0.68f)
                        "arco_anal" -> Offset(w * 0.60f, h * 0.50f)
                        "cuchara_anal" -> Offset(w * 0.50f, h * 0.61f)
                        else -> null
                    }
                    hipCenter?.let { center ->
                        drawCircle(
                            color = Color(0xFFFFB703).copy(alpha = 0.3f),
                            center = center,
                            radius = 20.dp.toPx(),
                            style = Stroke(width = 2.5.dp.toPx())
                        )
                        drawCircle(
                            color = Color(0xFFFFB703).copy(alpha = 0.15f),
                            center = center,
                            radius = 32.dp.toPx(),
                            style = Stroke(width = 1.5.dp.toPx())
                        )
                    }
                }
            }
        }
    }
}
