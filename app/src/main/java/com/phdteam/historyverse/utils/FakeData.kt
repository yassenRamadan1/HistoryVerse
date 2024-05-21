package com.phdteam.historyverse.utils

import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.ui.presentation.favorite.CardUiState
import kotlin.random.Random


class FakeData {
    fun generateFakeCards(): List<CardUiState> {
        val cards = mutableListOf<CardUiState>()
        val statueNames =
            listOf("Pharaoh", "Vase", "Statue", "Alex", "Athena") // Add more names as needed
        val imageUrls = listOf(
            "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",

            )

        for (i in 1..20) {
            val statueName = statueNames[i % statueNames.size]
            val imageUrl = imageUrls[i % imageUrls.size]
            val rating = (i % 11) * 0.5 // This will generate ratings 0.0, 0.5, 1.0, ..., 5.0

            cards.add(
                CardUiState(
                    cardTitleName = statueName,
                    favorite = Random.nextBoolean(),
                    imageUrl = imageUrl,
                    ratingAvg = rating,
                    museumName = "Museum $i",
                    museumImageUrl = R.drawable.hvlogo, // replace with your drawable resource
                    cardId = i.toString()
                )
            )
        }
        return cards
    }
}