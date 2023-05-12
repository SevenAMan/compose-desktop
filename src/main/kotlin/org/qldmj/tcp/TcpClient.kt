package org.qldmj.tcp

import java.io.File
import java.io.FileInputStream
import java.net.Socket

fun main() {
    val socket = Socket("127.0.0.1", 8601)
    val stream = socket.getOutputStream()

    FileInputStream(File("icon.ico")).buffered().use { fileIs ->
        val bytes = ByteArray(1024)
        var b: Int
        while ((fileIs.read(bytes).also { b = it }) != -1) {
            stream.write(bytes, 0, b)
        }
        socket.shutdownOutput()
    }
    socket.getInputStream().buffered().use { inputStream ->
        val rb = ByteArray(1024)
        var len: Int
        var string = ""
        while (inputStream.read(rb).also { len = it } != -1) {
            val s = String(rb, 0, len)
            string += s
            len = inputStream.read(rb)
        }
        println("Accept: $string \n")
    }
    socket.close()
}