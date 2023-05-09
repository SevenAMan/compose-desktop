package org.qldmj.my

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.isCtrlPressed
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication

data class Tabbed(val title: String, val canClose: Boolean = false, val content: @Composable () -> Unit)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tabbed(tabbed: MutableList<Tabbed>) {
    val closeables = remember { mutableStateListOf(false) }
    val tabbies: MutableList<@Composable BoxScope.() -> Unit> = remember { mutableListOf() }
    val mainContent: MutableList<@Composable ColumnScope.() -> Unit> = remember { mutableListOf() }

    for (t in tabbed) {
        key(t) {
            closeables.add(t.canClose)
            tabbies.add { Text(t.title, modifier = Modifier.align(Alignment.Center).padding(start = 5.dp, end = 5.dp)) }
            mainContent.add { t.content() }
        }
    }

    var selected by remember { mutableStateOf(0) }
    Column {
        Row {
            tabbies.forEachIndexed { index, tabbed ->
                Box(
                    modifier = Modifier
                        .size(60.dp, 30.dp)
                        .background(if (index == selected) Color.DarkGray.copy(alpha = 0.5f) else Color.LightGray.copy(alpha = 0.5f))
                        .onClick {
                            selected = index
                        }
                        .onClick(matcher = PointerMatcher.mouse(PointerButton.Secondary), keyboardModifiers = { isCtrlPressed }) {
                            closeables.removeAt(index)
                            tabbies.removeAt(index)
                            mainContent.removeAt(index)
                        }
                ) {
                    tabbed()
                }
            }
        }

        Row(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
            mainContent.forEachIndexed { index, content ->
                Column {
                    if (index == selected) {
                        content()
                    }
                }
            }
        }
    }
}

fun main() = singleWindowApplication {

    val item: MutableList<Tabbed> = mutableListOf<Tabbed>().apply {
        repeat(5) { index ->
            add(Tabbed("Tabb : $index", false) { Text("Tabb : $index") })
        }
    }
    Box {
        tabbed(item)
    }
}
