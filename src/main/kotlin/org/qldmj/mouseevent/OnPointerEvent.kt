package org.qldmj.mouseevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() = singleWindowApplication {

    var color by remember { mutableStateOf(Color(0, 0, 0)) }

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Box(modifier = Modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(color)
            .onPointerEvent(PointerEventType.Move) {
                val position = it.changes.first().position
                color = Color(position.x.toInt() % 256, position.y.toInt() % 256, 0)
            }
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.button.isSecondary && event.type == PointerEventType.Release) {
                            color = Color.Black //二键的事件
                        }
                    }
                }
            }
        )

        var scrollY by remember { mutableStateOf(0f) }
        var text by remember { mutableStateOf("") }
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f)
            .onPointerEvent(PointerEventType.Scroll) {
                scrollY += it.changes.first().scrollDelta.y

            }) {
            Text(text = "$text : $scrollY", fontSize = 30.sp)
        }

        Column(
            Modifier.background(Color.White),
            verticalArrangement = Arrangement.spacedBy(10.dp) //垂直间距
        ) {
            repeat(10) { index ->
                var active by remember { mutableStateOf(false) }
                Text(
                    text = "Item $index",
                    fontSize = 30.sp,
                    fontStyle = if (active) FontStyle.Italic else FontStyle.Normal,
                    modifier = Modifier.fillMaxWidth()
                        .background(if (active) Color.Green else Color.White)
                        .onPointerEvent(PointerEventType.Enter) { active = true }
                        .onPointerEvent(PointerEventType.Exit) { active = false }
                        .onPointerEvent(PointerEventType.Press) {
                            val source = it.awtEventOrNull?.source
                            text = source.toString()
                        }
                )
            }
        }
    }

}