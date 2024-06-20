package com.phdteam.historyverse.data.network.repositories

import com.google.ai.client.generativeai.Chat
import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.local.database.HistoryVerseDao
import com.phdteam.historyverse.data.network.BaseRepository
import com.phdteam.historyverse.data.network.model.Advertisement
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.service.GeminiApi
import com.phdteam.historyverse.data.network.service.HistoryVerseService

class HistoryVerseRepositoryImp(
    private val historyVerseDao: HistoryVerseDao,
    private val geminiApi: GeminiApi,
    private val historyVerseService: HistoryVerseService
): BaseRepository(),HistoryVerseRepository {
    override suspend fun getMentors(): List<Mentor> {
        return generatorMentor()
    }

    override suspend fun getMuseumsTypes(): List<MuseumType> {
        return generateMuseumsTypes()
    }

    override suspend fun getUniversities(): List<University> {
        return generateUniversities()
    }

    override fun generateContent(userContent: String, modelContent: String): Chat {
        return geminiApi.generateContent(userRole = userContent, modelRole = modelContent)
    }

    override suspend fun getArtifacts(): List<Artifact> {
        return try {
            historyVerseService.getArtifacts().let { response ->
                     response.body() ?: emptyList()
            }
        } catch (e: Exception){
            throw e
        }
    }

    override fun getAdvertisement(): List<Advertisement> {
        return generateAdvertisement()
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

    private fun generateAdvertisement(): List<Advertisement> {
        val list = mutableListOf<Advertisement>()
        for (i in 0..2) {
            list.add(
                Advertisement(
                    title = "Title $i",
                    imageUrl = getAdvertisementImages()[i],
                    description = "Description $i",
                )
            )
        }
        return list
    }

    private fun getAdvertisementImages(): List<String> {
        return listOf(
            "https://i.ibb.co/3sPwSKZ/all.png",
            "https://i.ibb.co/WH6xQCz/egypt.png",
            "https://i.ibb.co/J5jfQ0F/roman.png",
        )
    }


    private fun getProfileImage(): String {
        val list = listOf(
            "https://www.tn.gov/content/dam/tn/environment/archaeology/images/sellars-farm/arch_Sandy_DavidDye.jpg",
            "https://static01.nyt.com/images/2023/02/23/multimedia/21yemen-art1-zvjk/21yemen-art1-zvjk-superJumbo.jpg",
            "https://images.ctfassets.net/cex3swddvjuk/53kkeych9UdjbdjEZRqUWK/15a70aa14ffae58f72e1fa7cc51b9cf6/Ramses_the_Great_and_the_Gold_of_The_Pharaohs_Photo_15.jpg",
            "https://news.artnet.com/app/news-upload/2014/02/Romanhead.jpg",
            "https://www.ice.gov/sites/default/files/images/news/210924cpaa4.jpg",
            "https://cdn.britannica.com/81/156081-004-A65E877B/Statue-Indus-priest-nobleman-steatite-Mohenjo-dar-National-Museum-Pakistan-Karachi.jpg",
            "https://imonkey-blog.imgix.net/blog/wp-content/uploads/2015/12/23052256/shutterstock_242036125-400x600.jpg",
        )

        return list.shuffled().first()
    }

    private fun generateMuseumsTypes(): List<MuseumType> {
        return listOf(
            MuseumType(id = "1", name = "Roman History Museum"),
            MuseumType(id = "2", name = "Pharaoh Museum"),
            MuseumType(id = "3", name = "Medieval Museum"),
            MuseumType(id = "4", name = "Renaissance Museum"),
            MuseumType(id = "5", name = "Ancient Greek Museum"),
            MuseumType(id = "6", name = "Egyptian Museum"),
            MuseumType(id = "7", name = "Viking Museum"),
            MuseumType(id = "8", name = "Byzantine Museum"),
            MuseumType(id = "9", name = "Mesoamerican Museum"),
            MuseumType(id = "10", name = "Asian Art Museum")
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
            "https://media.architecturaldigest.com/photos/55e76762cd709ad62e8e8d4e/master/w_1920%2Cc_limit/dam-images-architecture-2015-09-university-art-museums-university-art-museums-01.jpg",
            "https://offloadmedia.feverup.com/secretldn.com/wp-content/uploads/2018/10/22055057/Museums-1024x683.jpg",
            "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/merlin_170735367_86ebb919-8e52-4cba-8b39-8f16c5bfb52b-jumbo.jpg",
            "https://i0.wp.com/theluxurytravelexpert.com/wp-content/uploads/2020/11/THE-RIJKSMUSEUM-AMSTERDAM-THE-NETHERLANDS.jpg?ssl=1",
            "https://i0.wp.com/theluxurytravelexpert.com/wp-content/uploads/2020/11/THE-VATICAN-MUSEUMS-VATICAN-CITY-ITALY.jpg?ssl=1",
        )

        return list.shuffled().first()
    }

    //endregion
}
