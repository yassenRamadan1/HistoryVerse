package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Advertisement
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
        getAdvertisement()
    }

    private fun getMentors() {
        tryToExecute(
            repository::getArtifacts,
            ::onSuccessMentors,
            ::onError
        )
    }

    private fun getAdvertisement() {
        tryToExecute(
            repository::getAdvertisement,
            ::onSuccessAdvertisement,
            ::onError
        )
    }

    private fun onSuccessAdvertisement(advertisement: List<Advertisement>) {
        updateState { it.copy(advertisement = advertisement, isLoading = false) }
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

    private fun onSuccessSubject(subjects: List<MuseumType>) {
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

