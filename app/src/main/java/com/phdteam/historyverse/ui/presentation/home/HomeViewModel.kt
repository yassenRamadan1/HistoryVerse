package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumsTypes
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class HomeViewModel(
    private val repository: HistoryVerseRepository
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()) {

    init {
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        getMentors()
        getSubjects()
        getUniversities()
    }

    private fun getMentors() {
        tryToExecute(
            repository::getArtifacts,
            ::onSuccessMentors,
            ::onError
        )
    }

    private fun onSuccessMentors(artifact: List<Artifact>) {
        updateState { it.copy(artifacts = artifact.toArtifactUiState(), isLoading = false) }
    }

    private fun getSubjects() {
        tryToExecute(
            repository::getMuseumsTypes,
            ::onSuccessSubject,
            ::onError
        )
    }

    private fun onSuccessSubject(subjects: List<MuseumsTypes>) {
        updateState { it.copy(subjects = subjects.take(6).toSubjectUiState()) }
    }

    private fun getUniversities() {
        tryToExecute(
            repository::getUniversities,
            ::onSuccessUniversity,
            ::onError
        )
    }

    private fun onSuccessUniversity(universities: List<University>) {
        updateState {
            it.copy(
                university = universities.take(6).toUniversityUiState()
            )
        }
    }

    private fun onError() {
        updateState { HomeUIState(isError = true, isLoading = false) }
        sendNewEffect(HomeUIEffect.HomeError)
    }


}

