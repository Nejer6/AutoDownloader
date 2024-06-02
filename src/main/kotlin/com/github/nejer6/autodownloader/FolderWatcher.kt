package com.github.nejer6.autodownloader

import java.nio.file.FileSystems
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds

class FolderWatcher(
    directory: String,
    private val minCount: Int,
    private val callback: () -> Unit
) {
    private val directoryPath = Paths.get(directory)
    private val watchService = FileSystems.getDefault().newWatchService()

    init {
        directoryPath.register(watchService, StandardWatchEventKinds.ENTRY_DELETE)

        while (true) {
            val key = watchService.take()
            key.pollEvents().forEach { _ ->
                val files = directoryPath.toFile().listFiles()
                if (files != null && files.size < minCount) {
                    callback()
                }
            }
            if (!key.reset()) {
                break
            }
        }
    }
}