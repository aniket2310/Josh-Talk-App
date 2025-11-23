package com.example.joshtalk.platform

import androidx.room.RoomDatabaseConstructor
import com.example.joshtalk.room.AppDatabase


expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>