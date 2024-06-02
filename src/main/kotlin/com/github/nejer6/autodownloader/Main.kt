package com.github.nejer6.autodownloader

import java.io.File

fun main() {
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
    readln()
}
