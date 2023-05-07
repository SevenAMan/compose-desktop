package org.qldmj.my

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.singleWindowApplication

@Composable
fun line(modifier: Modifier, number: String, lineText: String) {
    Row(modifier = modifier) {
        DisableSelection {  //禁用其中的文本框或文本域
            Box {
                lineNumber("100", Modifier.alpha(0f))
                lineNumber(number, Modifier.align(Alignment.CenterEnd))
            }
        }

        lineContent(Modifier
            .weight(1f)
            .withoutWidthConstraints()
            .padding(start = 28.dp, end = 12.dp), lineText)
    }
}
@Composable
private fun lineNumber(number: String, modifier: Modifier, fontSize: TextUnit = 23.sp) = Text(
    text = number,
    fontSize = fontSize,
    fontFamily = Fonts.jetbrainsMono(),
    color = LocalContentColor.current.copy(alpha = 0.30f),
    modifier = modifier.padding(start = 12.dp)
)
@Composable
private fun lineContent(modifier: Modifier, text: String, fontSize: TextUnit = 23.sp) = Text(
    text = text,
    fontSize = fontSize,
    fontFamily = Fonts.jetbrainsMono(),
    maxLines = 1,
    modifier = modifier,
    softWrap = false
)

fun Modifier.withoutWidthConstraints() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.copy(maxWidth = Int.MAX_VALUE))
    layout(constraints.maxWidth, placeable.height) {
        placeable.place(0, 0)
    }
}

private fun font(res: String, weight: FontWeight, style: FontStyle): Font =
    androidx.compose.ui.text.platform.Font("font/$res.ttf", weight, style)

object Fonts {
    @Composable
    fun jetbrainsMono() = FontFamily(
        font(
            "jetbrainsmono_regular",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        font(
            "jetbrainsmono_italic",
            FontWeight.Normal,
            FontStyle.Italic
        ),

        font(
            "jetbrainsmono_bold",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        font(
            "jetbrainsmono_bold_italic",
            FontWeight.Bold,
            FontStyle.Italic
        ),

        font(
            "jetbrainsmono_extrabold",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        font(
            "jetbrainsmono_extrabold_italic",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),

        font(
            "jetbrainsmono_medium",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        font(
            "jetbrainsmono_medium_italic",
            FontWeight.Medium,
            FontStyle.Italic
        )
    )
}

fun main() = singleWindowApplication {

    Column (Modifier.fillMaxSize()) {

        repeat(15) { index ->
            Box {
                SelectionContainer {
                    line(Modifier.align(Alignment.CenterStart), index.toString(), "Line text $index")
                }
            }
        }
    }

}