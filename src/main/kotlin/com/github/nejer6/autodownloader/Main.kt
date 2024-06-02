package com.github.nejer6.autodownloader

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking {
    val directory = "/data"

    val folderWatcher = FolderWatcher(
        directory = directory,
        minCount = 1
    ) {
        val file = File("$directory/test.txt")
        file.createNewFile()
    }
    folderWatcher.startWatching()
    print("Stop?")
    delay(1000 * 60 * 10)
}
