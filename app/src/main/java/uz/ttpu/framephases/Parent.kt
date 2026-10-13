package uz.ttpu.framephases

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Parent() {
    var count by remember { mutableIntStateOf(0) }
    SideEffect { Log.d("Skip", "Parent: composition") }

    Column {
        Button(onClick = { count++ }) {
            Text("Add")
        }
        // Pass a lambda instead of the raw value
        Child(count = { count })
        Sibling()
    }
}

@Composable
fun Child(count: () -> Int) {
    SideEffect { Log.d("Skip", "Child: composition") }
    // State is read here inside Child, keeping it out of Parent's restart scope
    Text("Count: ${count()}")
}

@Composable
fun Sibling() {
    SideEffect { Log.d("Skip", "Sibling: composition") }
    Text("I never change")
}