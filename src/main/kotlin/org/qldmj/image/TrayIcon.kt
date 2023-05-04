package org.qldmj.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    application {

        var visible by remember { mutableStateOf(true) }
        var item by remember { mutableStateOf("Hidden") }

        val icon = painterResource("image/sample.png")
        Tray(icon, menu = {
            Item("Exit", onClick = ::exitApplication)
            Item(item, onClick = {
                visible = !visible
                item = if (!visible) "Display" else "Hidden"
            })

        })

        Window(icon = icon, onCloseRequest = ::exitApplication, visible = visible) {
            Image(icon, contentDescription = null, modifier = Modifier.fillMaxWidth())
        }
    }
}