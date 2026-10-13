package uz.ttpu.framephases

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.layout

/**
 * Логирует фазы layout (измерение и размещение) и фазу drawing
 * для элемента, к которому применен этот модификатор.
 */
fun Modifier.logPhases(name: String): Modifier = this
    .layout { measurable, constraints ->
        Log.d("Phases", "$name: layout/measure")
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            Log.d("Phases", "$name: layout/place")
            placeable.place(0, 0)
        }
    }
    .drawBehind {
        Log.d("Phases", "$name: draw")
    }