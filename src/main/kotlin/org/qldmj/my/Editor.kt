package org.qldmj.my

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication
import java.io.File

fun main() = singleWindowApplication {
    Box {

        val v = rememberScrollState(0)
        val h = rememberScrollState(0)

        Box(Modifier.fillMaxSize().verticalScroll(v)) {
            val lines = File("note.md").readLines()
            Row(Modifier.align(Alignment.CenterStart)) {
                Box {
                    numColumn(20.sp, lines.size)
                }
                Box(Modifier.horizontalScroll(h).fillMaxWidth()) {
                    contentColumn(20.sp, lines)
                }
            }
        }
        VerticalScrollbar(rememberScrollbarAdapter(v), Modifier.align(Alignment.CenterEnd).fillMaxHeight())
        HorizontalScrollbar(
            rememberScrollbarAdapter(h),
            Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(end = 12.dp).background(Color.Red)
        )
    }
}

@Composable
private fun numColumn(textSize: TextUnit, lineSize: Int) {
    DisableSelection {  //禁用其中的文本框或文本域
        Column {
            repeat(lineSize) {
                Box {
                    lineNumber("10000", Modifier.alpha(0f), textSize)
                    lineNumber("$it", Modifier.align(Alignment.CenterEnd), textSize)
                }
            }
        }
    }
}

@Composable
private fun contentColumn(textSize: TextUnit, lineText: List<String>) {
    SelectionContainer {
        Column {
            repeat(lineText.size) {
                Box {
                    lineContent(modifier = Modifier.withoutWidthConstraints(), lineText[it], textSize)
                }
            }
        }
    }
}

@Composable
private fun lineContent(modifier: Modifier, text: String, fontSize: TextUnit = 23.sp) = Text(
    text = text,
    fontSize = fontSize,
    maxLines = 1,
    modifier = modifier.padding(start = 12.dp)
)

@Composable
private fun lineNumber(number: String, modifier: Modifier, fontSize: TextUnit = 23.sp) = Text(
    text = number,
    maxLines = 1,
    fontSize = fontSize,
    color = LocalContentColor.current.copy(alpha = 0.30f),
    modifier = modifier.padding(start = 12.dp)
)

private fun Modifier.withoutWidthConstraints() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.copy(maxWidth = Int.MAX_VALUE))
    layout(constraints.maxWidth, placeable.height) {
        placeable.place(0, 0)
    }
}