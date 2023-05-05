package org.qldmj.mouseevent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.isShiftPressed
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication

@OptIn(ExperimentalFoundationApi::class)
fun main() = singleWindowApplication {

    var text by remember { mutableStateOf("") }

    Column {
        Text(
            text, textAlign = TextAlign.Center, fontSize = 40.sp, fontFamily = FontFamily.Monospace,
            modifier = Modifier.background(Color.Green).fillMaxWidth()
        )

        Box(modifier = Modifier.size(200.dp, 100.dp).background(Color.Red)
            .onClick {
                text = "单击"
            }
            .onClick(keyboardModifiers = { isShiftPressed }) {
                text = "点击并按下了 shift "
            }
        )


    }


}