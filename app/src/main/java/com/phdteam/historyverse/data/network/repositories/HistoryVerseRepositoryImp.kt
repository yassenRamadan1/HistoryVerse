package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.data.local.database.HistoryVerseDao
import com.phdteam.historyverse.data.network.BaseRepository

class HistoryVerseRepositoryImp(
    private val historyVerseDao: HistoryVerseDao
): BaseRepository(),HistoryVerseRepository {
}