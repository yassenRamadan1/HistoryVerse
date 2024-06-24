package com.phdteam.historyverse.ui.presentation.details

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum
import com.phdteam.historyverse.ui.presentation.home.ArtifactUiState

data class DetailsScreenUiState(
    val artifacts: List<ArtifactUiState> = emptyList(),
    val museum: MuseumDetailsUiState = MuseumDetailsUiState(),
    val artifactDetails : ArtifactDetailsUiState= ArtifactDetailsUiState(),
    val details : DetailsUiState = DetailsUiState(),
    val reviewState: ReviewTabState = ReviewTabState(),
    val recommendedArtifacts : List<ArtifactUiState> = emptyList(),
    val mostPopularArtifacts : List<ArtifactUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)
data class ArtifactDetailsUiState(
    val id: Int = 0,
    val name: String = "",
    val artifactType: String = "",
    val artifactDescription : String = "",
    val imageUrl: String = "",
    val artifactHistory: String = "",
)
data class MuseumDetailsUiState(
    val museumId: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val city: String = "",
    val description: String = "",
    val rating: Float = 0.0f,
)

data class DetailsUiState(
    val name :String = "" ,
    val rating: Float = 4.0f,
    val description: String = "",
    val isMuseum: Boolean = false,
    val imageUrl: String = "",
)
fun Artifact.toDetailsUiState() = DetailsUiState(
    name = name ?: "",
    rating = 4.0f,
    description = artifactDescription ?: "",
    isMuseum = false,
    imageUrl = artifactImageUrl ?: "",
)
fun Museum.toDetailsUiState() = DetailsUiState(
    name = name ?: "",
    rating = 4.0f,
    description = museumDescription ?: "",
    isMuseum = true,
    imageUrl = imageUrl ?: "",
)
fun Artifact.toArtifactDetailsUiState() = ArtifactDetailsUiState(
    id = id ?: 0,
    name = name ?: "",
    artifactType = artifactType ?: "",
    artifactDescription = artifactDescription ?: "",
    imageUrl = artifactImageUrl ?: "",
    artifactHistory = artifactHistory ?: "",
)
fun Museum.toMuseumDetailsUiState() = MuseumDetailsUiState(
    museumId = museumId ?: 0,
    name = name ?: "",
    imageUrl = imageUrl ?: "",
    city = city ?: "",
    description = museumDescription ?: "",
    rating = 0.0f,
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

