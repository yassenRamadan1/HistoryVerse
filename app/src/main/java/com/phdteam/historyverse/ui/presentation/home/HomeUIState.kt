package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.network.model.Advertisement
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum

data class HomeUIState(
    val artifacts: List<ArtifactUiState> = emptyList(),
    val museums: List<MuseumUiState> = emptyList(),
    val categories: List<String?> = emptyList(),
    val fakeCategories: List<CategoryUiState> = emptyList(),
    val advertisement: List<Advertisement> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


data class ArtifactUiState(
    val id: Int = 0,
    val name: String = "",
    val artifactType: String = "",
    val artifactDescription : String = "",
    val imageUrl: String = "",
    val artifactHistory: String = "",
    val museumId : Int = 0 ,
)
data class MuseumUiState(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val city : String = "",
    )
fun List<Any>.showSeeAll(): Boolean {
    return this.size > 3
}
data class CategoryUiState(
    val id: String = "",
    val name: String = ""
)


//region Mappers

fun Artifact.toArtifactUiState() = ArtifactUiState(
    id = id ?: 0,
    name = name ?: "",
    artifactType = artifactType ?: "",
    artifactDescription = artifactDescription ?: "",
    imageUrl = artifactImageUrl ?: "",
    artifactHistory = artifactHistory ?: "",
    museumId = museumId ?: 0,
)
fun Museum.toMuseumUiState() = MuseumUiState(
    id = museumId ?: 0,
    name = name ?: "",
    imageUrl = imageUrl ?: "",
    city = city ?: ""
)
fun List<Museum>.toMuseumUiState() = map { it.toMuseumUiState() }
fun List<Artifact>.toArtifactUiState() = map { it.toArtifactUiState() }

fun MuseumType.toSubjectUiState() = CategoryUiState(id, name)

fun List<MuseumType>.toSubjectUiState() = map { it.toSubjectUiState() }


//endregion
