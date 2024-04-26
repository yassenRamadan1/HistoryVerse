package com.phdteam.historyverse.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MINDFUL_MENTOR")
class HistoryVerseEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,

    )