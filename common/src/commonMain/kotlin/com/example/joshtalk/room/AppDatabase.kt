package com.example.joshtalk.room


import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.joshtalk.platform.AppDatabaseConstructor


@Database(entities = [TaskEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
