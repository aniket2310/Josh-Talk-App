package com.example.joshtalk.platform


import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.joshtalk.room.AppDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val documentDir = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    val dbPath = documentDir?.path + "/joshtalk.db"

    return Room.databaseBuilder<AppDatabase>(
        name = dbPath
    )
}