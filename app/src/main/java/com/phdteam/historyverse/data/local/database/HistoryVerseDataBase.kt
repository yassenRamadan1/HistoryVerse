package com.phdteam.historyverse.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.phdteam.historyverse.data.local.Converters

@Database(entities = [HistoryVerseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class HistoryVerseDataBase : RoomDatabase() {

    abstract fun historyVerseDao(): HistoryVerseDao
}