package com.phdteam.historyverse.ui.presentation.details

import com.phdteam.historyverse.ui.presentation.favorite.CardUiState

data class MuseumDetailsUiState(
    val museam: String = "Alexandria Museum",
    val description: String = "Lorem ipsum dolor sit amet consectetur. Neque rutrum egestas tristique urna. Tortor netus dui vitae risus fermentum viverra fringilla. Nunc sollicitudin fames cras diam adipiscing ante gravida. Tellus mus volutpat eget nisi tristique consequat amet.",
    val imageUrl: String = "",
    val rating: Float = 10f,
    val isLoading: Boolean = false,
    val categories: List<String> = listOf("History", "Art", "Science", "Culture"),
    val isFavorite: Boolean = false,
    val isBooked: Boolean = false,
    val isSaved: Boolean = false,
    val recommendedArtifacts: List<CardUiState> = artifactList,
    val mostPopularArtifacts: List<CardUiState> = artifactList,
    val recommendedProducts: List<CardUiState> = artifactList,
    val mostSoldProducts: List<CardUiState> = artifactList,
    val reviewState: ReviewTabState = ReviewTabState(),
    val museumId: Int = 0,

    )

data class ReviewTabState(
    val review: Int = 0,
    val reviews: List<Review> = reviewList,
)

data class Review(
    val review: String = "Lorem ipsum dolor sit amet consectetur. Neque rutrum egestas tristique urna. Tortor netus dui vitae risus fermentum viverra fringilla. Nunc sollicitudin fames cras diam adipiscing ante gravida. Tellus mus volutpat eget nisi tristique consequat amet.",
    val rating: Int = 3,
    val date: String = "2023-04-10",
    val userName: String = "UnknownUser",
    val userImageUrl: String = "https://cdn.pixabay.com/photo/2022/12/01/04/42/man-7628305_1280.jpg",
    val reviewId: Int = 0,
)

private val reviewList = listOf(
    Review(),
    Review(),
    Review(),
    Review(),
    Review(),
    Review(),
)

data class Artifact(
    val name: String = "Tikana",
    val image: String = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    val id: Int = 0,
)

private val artifactList = listOf(
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
    CardUiState(
        cardTitleName = "Tikana",
        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        ratingAvg = 3.5,
        museumName = "Alexandria Museum",
    ),
)
