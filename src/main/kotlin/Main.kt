import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun app() {

    val defaultText = "Hello, World!"

    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {

        Column {
            TextField(value = text, onValueChange = { text = it })

            Row {
                Button(onClick = {
                    text = defaultText
                }) {
                    Text("重置")
                }
                Button(onClick = {
                    text = ""
                }) {
                    Text("清空")
                }
            }
            Text(text, modifier = Modifier.fillMaxWidth())
        }

    }
}

fun main() = application {
    Window(title = "compose desktop", onCloseRequest = ::exitApplication) {
        app()
    }
}
