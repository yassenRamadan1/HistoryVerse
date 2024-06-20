package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumsTypes
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Artifact

data class HomeUIState(

    val artifacts: List<ArtifactUiState> = emptyList(),
    val subjects: List<SubjectUiState> = emptyList(),
    val university: List<UniversityUiState> = emptyList(),

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
fun List<Any>.showSeeAll(): Boolean {
    return this.size > 3
}
data class SubjectUiState(
    val id: String = "",
    val name: String = ""
)


data class UniversityUiState(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val imageUrl: String = ""
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


fun List<Artifact>.toArtifactUiState() = map { it.toArtifactUiState() }

fun MuseumsTypes.toSubjectUiState() = SubjectUiState(id, name)

fun List<MuseumsTypes>.toSubjectUiState() = map { it.toSubjectUiState() }


fun University.toUniversityUiState() = UniversityUiState(
    id = id,
    imageUrl = imageUrl,
    name = name,
    address = address
)

fun List<University>.toUniversityUiState() = map { it.toUniversityUiState() }

//endregion
