package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.MuseumType
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.model.Advertisement
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum
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
        getArtifacts()
        getSubjects()
        getAdvertisement()
        getMuseums()
    }

    private fun getArtifacts() {
        updateState { it.copy(isLoading = true)}
        tryToExecute(
            repository::getArtifacts,
            ::onSuccessArtifacts,
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
    private fun getMuseums() {
        updateState { it.copy(isLoading = true)}
        tryToExecute(
            repository::getMuseums,
            ::onSuccessMuseums,
            ::onError
        )
    }
    private fun onSuccessMuseums(museums: List<Museum>) {
        updateState { it.copy(museums = museums.take(6).shuffled().toMuseumUiState(), isLoading = false) }
    }
    private fun onSuccessAdvertisement(advertisement: List<Advertisement>) {
        updateState { it.copy(advertisement = advertisement, isLoading = false) }
    }
    private fun onSuccessArtifacts(artifact: List<Artifact>) {
        updateState { it.copy(artifacts = artifact.take(6).toArtifactUiState(), isLoading = false) }
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

    private fun onError() {
        updateState { HomeUIState(isError = true, isLoading = false) }
        sendNewEffect(HomeUIEffect.HomeError)
    }


}

