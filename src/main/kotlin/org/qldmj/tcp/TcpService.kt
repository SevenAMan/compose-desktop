package org.qldmj.tcp

import java.io.FileOutputStream
import java.net.ServerSocket

fun main() {

    val path = "ico.png"
    val server = ServerSocket(8601)
    val socket = server.accept()
    val inputStream = socket.getInputStream()
    FileOutputStream(path).buffered().use { fileOut ->
        val bytes = ByteArray(1024)
        var len: Int
        while ((inputStream.read(bytes).also { len = it }) != -1) {
            fileOut.write(bytes, 0, len)
        }
        socket.getOutputStream().buffered().use {
            it.write(path.toByteArray())
        }
    }
    socket.close()
    server.close()
}