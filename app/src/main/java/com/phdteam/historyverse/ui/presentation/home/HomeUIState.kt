package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Advertisement
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum

data class HomeUIState(
    val artifacts: List<ArtifactUiState> = emptyList(),
    val museums: List<MuseumUiState> = emptyList(),
    val subjects: List<SubjectUiState> = emptyList(),
    val advertisement: List<Advertisement> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


data class ArtifactUiState(
    val id: String = "",
    val name: String = "",
    val artifactType: String = "",
    val artifactDescription : String = "",
    val imageUrl: String = "",
    val artifactHistory: String = "",

)
data class MuseumUiState(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val city : String = "",
    )
fun List<Any>.showSeeAll(): Boolean {
    return this.size > 3
}
data class SubjectUiState(
    val id: String = "",
    val name: String = ""
)


//region Mappers

fun Artifact.toArtifactUiState() = ArtifactUiState(
    id = id.toString(),
    name = name ?: "",
    artifactType = artifactType ?: "",
    artifactDescription = artifactDescription ?: "",
    imageUrl = artifactImageUrl ?: "",
    artifactHistory = artifactHistory ?: ""
)
fun Museum.toMuseumUiState() = MuseumUiState(
    id = museumId.toString(),
    name = name ?: "",
    imageUrl = imageUrl ?: "",
    city = city ?: ""
)
fun List<Museum>.toMuseumUiState() = map { it.toMuseumUiState() }
fun List<Artifact>.toArtifactUiState() = map { it.toArtifactUiState() }

fun MuseumType.toSubjectUiState() = SubjectUiState(id, name)

fun List<MuseumType>.toSubjectUiState() = map { it.toSubjectUiState() }


//endregion
