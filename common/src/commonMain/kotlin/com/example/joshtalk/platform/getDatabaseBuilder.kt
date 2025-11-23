package com.example.joshtalk.platform

import androidx.room.RoomDatabase
import com.example.joshtalk.room.AppDatabase


expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>