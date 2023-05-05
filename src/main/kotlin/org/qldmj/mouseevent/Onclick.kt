package org.qldmj.mouseevent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
fun main() = singleWindowApplication {

    var text by remember { mutableStateOf("") }

    Column {
        Box {
            AnimatedContent(targetState = text, modifier = Modifier.align(Alignment.Center)) {
                Text(
                    it, textAlign = TextAlign.Center, fontSize = 40.sp, fontFamily = FontFamily.Monospace,
                    modifier = Modifier.background(Color.Green).fillMaxWidth()
                )
            }
        }

        Box(modifier = Modifier.size(200.dp, 100.dp).background(Color.Red)
            .onClick {
                text = "单击"
            }
            .onClick(keyboardModifiers = { isShiftPressed }) {
                text = "点击并按下了 shift "
            }
        ) {
            AnimatedContent(targetState = text, modifier = Modifier.align(Alignment.Center)) {
                Text(text)
            }
        }

        var bottomBoxText by remember { mutableStateOf("点击 \n 左键 or\n右键 + Alt") }
        var bottomBoxCount by remember { mutableStateOf(0) }
        val interactionSource = remember { MutableInteractionSource() }  //交互状态。  按压状态、  按压状态、  按压状态 等
        Box(modifier = Modifier.size(200.dp, 100.dp).background(Color.Yellow)
            .onClick(
                enabled = true,
                interactionSource = interactionSource,
                matcher = PointerMatcher.mouse(PointerButton.Secondary), //右键  matcher 选择应该触发单击的鼠标按钮
                keyboardModifiers = { isAltPressed },  // + Alt  允许过滤指定键盘修饰符
                onClick = {
                    bottomBoxText = "右键 + Alt ${bottomBoxCount++}"
                },
                onLongClick = {
                    bottomBoxText = "右键长按 + Alt ${bottomBoxCount++}"
                },
                onDoubleClick = {
                    bottomBoxText = "右键双击 + Alt ${bottomBoxCount++}"
                }
            )
            .onClick(interactionSource = interactionSource) {
                bottomBoxText = "左键 ${bottomBoxCount++}"
            }
            .indication(interactionSource, LocalIndication.current)  //在发生交互时为此组件绘制视觉效果。
        ) {
            AnimatedContent(
                targetState = bottomBoxText,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = it, textAlign = TextAlign.Center)
            }
        }


        val windowInfo = LocalWindowInfo.current
        var topBoxOffset by remember { mutableStateOf(Offset(0f, 0f)) }

        Box(modifier = Modifier
            .offset { IntOffset(topBoxOffset.x.toInt(), topBoxOffset.y.toInt()) }
            .size(100.dp)
            .background(Color.Green)
            .onDrag { // 默认配置: enabled = true, matcher = PointerMatcher.Primary(鼠标左键)
                topBoxOffset += it  //偏移量增加
            }
            .indication(interactionSource, LocalIndication.current)
        ) {
            Text(text = "左键拖拽", modifier = Modifier.align(Alignment.Center)/*位于容器的中央*/)
        }

        var bottomBoxOffset by remember { mutableStateOf(Offset(0f, 0f)) }

        Box(modifier = Modifier.offset {
            IntOffset(bottomBoxOffset.x.toInt(), bottomBoxOffset.y.toInt())
        }.size(100.dp)
            .background(Color.LightGray)
            .onDrag(
                enabled = true,
                matcher = PointerMatcher.mouse(PointerButton.Secondary), // right mouse button
                onDragStart = {
                    println("Gray Box: drag start")
                },
                onDragEnd = {
                    println("Gray Box: drag end")
                }
            ) {
                val keyboardModifiers = windowInfo.keyboardModifiers
                bottomBoxOffset += if (keyboardModifiers.isCtrlPressed) it * 2f else it
            }
        ) {
            Text(text = "右键拖拽,\n可以按下ctrl", modifier = Modifier.align(Alignment.Center))
        }
    }

}