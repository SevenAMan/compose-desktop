package org.qldmj.my

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.isCtrlPressed
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication

class Tabbed2 {
    var tabbies = mutableStateListOf<@Composable BoxScope.() -> Unit>()
    var mainContent = mutableStateListOf<@Composable ColumnScope.() -> Unit>()
    var item = mutableStateListOf(0)

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun create() {
        Column {
            Row {
                tabbies.forEachIndexed { index, tabbed ->
                    Box(
                        modifier = Modifier
                            .size(100.dp, 30.dp)
                            .background(
                                if (index == item[0]) Color.DarkGray.copy(alpha = 0.5f) else Color.LightGray.copy(
                                    alpha = 0.5f
                                )
                            )
                            .onClick {
                                setSelect(index)
                            }
                            .onClick(
                                matcher = PointerMatcher.mouse(PointerButton.Secondary),
                                keyboardModifiers = { isCtrlPressed }) { remove(item[0]) }
                    ) {
                        tabbed()
                    }
                }
            }

            Row(modifier = Modifier.fillMaxSize().background(Color.Gray)) {
                mainContent.forEachIndexed { index, content ->
                    Column {
                        if (index == item[0]) {
                            content()
                        }
                    }
                }

                if (tabbies.size == 0) {
                    Box(Modifier.fillMaxSize().onClick {
                        createTabbed("Empty tabbed panel", true)
                        createContent {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text("Empty tabbed panel", Modifier.align(Alignment.Center))
                            }
                        }

                    }) {
                        Text("点击添加一个Tabbed", Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }


}

private fun Tabbed2.remove(index: Int) {
    tabbies.removeAt(index)
    mainContent.removeAt(index)
    if (tabbies.size > 0) {
        setSelect(if (index == 0) 0 else index - 1)
    }
}

private fun Tabbed2.setSelect(index: Int) {
    item.clear()
    item.add(0, index)
}

@Composable
fun Tabbed2.addTabbed(title: String, canClose: Boolean = false, content: @Composable ColumnScope.() -> Unit) {
    createTabbed(title, canClose)
    createContent(content)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
private fun Tabbed2.createTabbed(title: String, canClose: Boolean = false) {
    tabbies.add {
        Row {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(title, modifier = Modifier.align(Alignment.CenterStart).padding(start = 5.dp, end = 5.dp))
                if (canClose) {

                    var over by remember { mutableStateOf(false) }

                    Box(modifier = Modifier
                        .onClick {
                            remove(item[0])
                        }
                        .onPointerEvent(PointerEventType.Enter) {
                            over = true
                        }
                        .onPointerEvent(PointerEventType.Exit) {
                            over = false
                        }
                        .align(Alignment.TopEnd).border(BorderStroke(1.dp, Color.Black), CircleShape)
                        .background(if (over) Color.Red else Color.Gray)) {
                        Text("X", modifier = Modifier.padding(start = 4.dp, end = 4.dp))
                    }
                }
            }
        }
    }
}

private fun Tabbed2.createContent(content: @Composable ColumnScope.() -> Unit) {
    mainContent.add { content() }
}

fun main() = singleWindowApplication {
    val tabbed = Tabbed2()
    repeat(5) { index ->
        tabbed.addTabbed("Tabb : $index", true) {
            Text("Tabb : $index")
        }
    }
    Box {
        tabbed.create()
    }
}
