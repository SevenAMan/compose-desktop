package org.qldmj.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.IOException
import java.io.InputStream
import java.net.URL

fun main() = singleWindowApplication {
    val density = LocalDensity.current// 本机像素
    Column {
        asyncImage(
            load = { useResource("image/wallpaper.png") { it.buffered().use(::loadImageBitmap) } },
            painterFor = { remember { BitmapPainter(it) } },
            contentDescription = "wallpaper",
            modifier = Modifier.width(200.dp)
        )

        asyncImage(
            load = { loadBySvgPainter(getInputStream("image/idea-log.svg"), density) },
            painterFor = { remember { it } },
            contentDescription = "idea log",
            contentScale = ContentScale.FillWidth,
//            modifier = Modifier.width(200.dp)
        )

        asyncImage(
            load = { loadByXmlImageVector(getInputStream("image/compose-log.xml"), density) },
            painterFor = { rememberVectorPainter(it) },
            contentDescription = "compose log",
            contentScale = ContentScale.FillWidth,
//            modifier = Modifier.width(200.dp)
        )

//        val url =""
//        asyncImage(
//            load = { loadByImageBitmap(url) },
//            painterFor = { remember { BitmapPainter(it) } },
//            contentDescription = "网图"
//        )
    }

}

/**
 * 异步图像
 */
@Composable
private fun <T> asyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                // instead of printing to console, you can also write this to log,
                // or show some error placeholder
                e.printStackTrace()
                null
            }
        }
    }

    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }

}

private fun getInputStream(resourcePath: String): InputStream {
    return ClassLoader.getSystemClassLoader().getResource(resourcePath)?.openStream()!!
}


//通过java IO加载图像, ImageBitmap: 位图，即普通图
/**
 * 加载位图
 */
private fun loadByImageBitmap(inputStream: InputStream): ImageBitmap =
    inputStream.buffered().use(::loadImageBitmap)

/**
 * @param inputStream      svg
 * @param density   屏幕的密度。用于像素之间的转换
 */
private fun loadBySvgPainter(inputStream: InputStream, density: Density): Painter =
    inputStream.buffered().use { loadSvgPainter(it, density) }


/**
 * @param inputStream      xml image
 * @param density   屏幕密度，像素之间的转换
 *
 * @return ImageVector矢量图
 */
private fun loadByXmlImageVector(inputStream: InputStream, density: Density): ImageVector =
    inputStream.buffered().use { loadXmlImageVector(InputSource(it), density) }

// 通过net IO 加载图象
private fun loadByImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)

private fun loadBySvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { loadSvgPainter(it, density) }

private fun loadByXmlImageVector(url: String, density: Density): ImageVector =
    URL(url).openStream().buffered().use { loadXmlImageVector(InputSource(it), density) }

