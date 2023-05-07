package org.qldmj.mouseevent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    var count by remember { mutableStateOf(0) }
    Window(
        icon = useResource("image/idea-log.svg") { loadSvgPainter(it, LocalDensity.current) },
        title = "鼠标点击事件", onCloseRequest = ::exitApplication
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            var text by remember { mutableStateOf("点击 Magenta 色的 组件") }
            Column {
                @OptIn(ExperimentalFoundationApi::class)
                Box(
                    modifier = Modifier.background(Color.Magenta)
                        .fillMaxWidth(0.7f).fillMaxHeight(0.2f)
                        .combinedClickable(
                            onClick = { text = "点击了${count++}次！ 单击" },
                            onDoubleClick = { text = "点击了${count++}次！ 双击" },
                            onLongClick = { text = "点击了${count++}次！ 长击" },
                            onClickLabel = "单"
                        )
                )
                Text(
                    text = text, fontSize = 40.sp, textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.7f).fillMaxHeight(0.2f)
                )
                var textFiledText by remember { mutableStateOf("") }
                TextField(textFiledText, onValueChange = { textFiledText = it })
            }
        }
    }
}

