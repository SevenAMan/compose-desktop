package org.qldmj.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication {

    //painterResource
    // 支持 位图：(BMP, GIF, HEIF, ICO, JPEG, PNG, WBMP, WebP)
    //     矢量图：(SVG, XML vector drawable)

    Image(
        painter = painterResource("image/wallpaper.png"),
        modifier = Modifier.fillMaxSize(),
        contentDescription = "壁纸"
    )
}