package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.Subject
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.local.database.HistoryVerseDao
import com.phdteam.historyverse.data.network.BaseRepository

class HistoryVerseRepositoryImp(
    private val historyVerseDao: HistoryVerseDao
): BaseRepository(),HistoryVerseRepository {
    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }

    override suspend fun getSubject(): List<Subject> {
        return generateSubjects()
    }

    override suspend fun getUniversities(): List<University> {
        return generateUniversities()
    }


    //region Fake Data

    private fun generatorMentor(): List<Mentor> {
        val list = mutableListOf<Mentor>()
        for (i in 0..10) {
            list.add(
                Mentor(
                    id = "$i",
                    name = "First Last$i",
                    imageUrl = getProfileImage(),
                    rate = (0..10).random().toDouble(),
                    numberReviewers = (1..500).random(),
                )
            )
        }
        return list
    }


    private fun getProfileImage(): String {
        val list = listOf(
            "https://www.tn.gov/content/dam/tn/environment/archaeology/images/sellars-farm/arch_Sandy_DavidDye.jpg",
            "https://static01.nyt.com/images/2019/02/16/arts/16met-coffin2/merlin_150744567_c26a5a1b-868e-4da9-8ac7-895fe8e80b87-superJumbo.jpg?quality=75&auto=webp",
            "https://scienceandsf.com/wp-content/uploads/2023/09/TUT-Ausstellung_FFM_2012_47_7117819557.jpg",
            "https://news.artnet.com/app/news-upload/2014/02/Romanhead.jpg"
        )

        return list.shuffled().first()
    }

    private fun generateSubjects(): List<Subject> {
        return listOf(
            Subject(id = "1", name = "Roman History Museum"),
            Subject(id = "2", name = "Pharaoh Museum"),
            Subject(id = "3", name = "Medieval Museum"),
            Subject(id = "4", name = "Renaissance Museum"),
            Subject(id = "5", name = "Ancient Greek Museum"),
            Subject(id = "6", name = "Egyptian Museum"),
            Subject(id = "7", name = "Viking Museum"),
            Subject(id = "8", name = "Byzantine Museum"),
            Subject(id = "9", name = "Mesoamerican Museum"),
            Subject(id = "10", name = "Asian Art Museum")
        )
    }

    private fun generateUniversities(): List<University> {
        val list = mutableListOf<University>()
        for (i in 0..10) {
            list.add(
                University(
                    id = "$i",
                    name = "First Last$i",
                    imageUrl = getImage(),
                    address = "Alexandria, Egypt"
                )
            )
        }
        return list
    }

    private fun getImage(): String {
        val list = listOf(
            "https://upload.wikimedia.org/wikipedia/commons/d/da/GD-EG-Alex-MuséeNat040.JPG",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQANtG6UPPvIwDcLr4wpV4pt3ixtkv8eHjlKg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY-Fzwk77TGkO86UCbElFcSkqwx1DcSI_bwQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsxt4TE55zRBBGspJT4FAm_pi1ZfDbLXGPUA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY9goD6bOsmy-JWoW-4u44Dp8tyR2WwpKlSw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq6qXvZ7aaZxvL2diHXHJ47C8J7NDJ2SLXaQ&usqp=CAU",
        )

        return list.shuffled().first()
    }

    //endregion
}
