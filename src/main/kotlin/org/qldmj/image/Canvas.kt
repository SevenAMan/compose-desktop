package org.qldmj.image

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.*
import androidx.compose.ui.window.singleWindowApplication
import org.xml.sax.InputSource

fun main() = singleWindowApplication(icon = BitmapPainter(useResource("image/sample.png"){ loadImageBitmap(it) })) {

    val density = LocalDensity.current /// 计算矢量图像（SVG，XML）的固有大小

    val sample = remember { useResource("image/sample.png", ::loadImageBitmap) }

    val ideaLogo = remember { useResource("image/idea-log.svg") { loadSvgPainter(it, density) } }

    val composeLogo = rememberVectorPainter(remember {
        useResource("image/compose-log.xml") {
            loadXmlImageVector(
                InputSource(it), density
            )
        }
    })

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawIntoCanvas { canvas ->
            canvas.withSave {
                canvas.drawImage(sample, Offset.Zero, Paint())
                canvas.translate(0f, sample.height.toFloat())  //以动画笔
                with(ideaLogo) {
                    draw(ideaLogo.intrinsicSize)
                }
                canvas.translate(0f, ideaLogo.intrinsicSize.height)
                with(composeLogo) {
                    draw(Size(100f, 100f))
                }
            }
        }
    }
}