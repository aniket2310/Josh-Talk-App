package com.example.joshtalk.platform


import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.joshtalk.AndroidContext
import com.example.joshtalk.room.AppDatabase
import java.io.File


actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val ctx = AndroidContext.applicationContext


    val dbDir = ctx.filesDir
    val dbFile = File(dbDir, "joshtalk.db")

    println("📁 Database path: ${dbFile.absolutePath}")
    println("📁 Database directory exists: ${dbDir.exists()}")
    println("📁 Database directory writable: ${dbDir.canWrite()}")

    return Room.databaseBuilder<AppDatabase>(
        context = ctx,
        name = dbFile.absolutePath
    )
}