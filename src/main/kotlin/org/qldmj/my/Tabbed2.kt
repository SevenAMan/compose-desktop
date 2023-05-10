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

class Tabbed2 {
    var closeables: MutableList<Boolean> = mutableListOf()
    var tabbies = mutableStateListOf<@Composable BoxScope.() -> Unit>()
    var mainContent = mutableStateListOf<@Composable ColumnScope.() -> Unit>()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun create() {
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
                                tabbies.removeAt(selected)
                                mainContent.removeAt(selected)
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
}

private fun Tabbed2.remove(index: Int) {
    closeables.removeAt(index)
    tabbies.removeAt(index)
    mainContent.removeAt(index)
}

@Composable
private fun removeButton() {
    Box {

    }
}

@Composable
fun Tabbed2.addTabbed(title: String, canClose: Boolean = false, content: @Composable ColumnScope.() -> Unit) {
    canClose.and(canClose)
    tabbies.add {
        Text(title, modifier = Modifier.align(Alignment.Center).padding(start = 5.dp, end = 5.dp))
    }
    mainContent.add { content() }
}

fun main() = singleWindowApplication {
    val tabbed = Tabbed2()
    repeat(5) { index ->
        tabbed.addTabbed("Tabb : $index") {
            Text("Tabb : $index")
        }
    }
    Box {
        tabbed.create()
    }
}
