package com.phdteam.historyverse.ui.presentation.seeall

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.toArtifactUiState
import com.phdteam.historyverse.ui.presentation.home.toMuseumUiState

class SeeAllViewModel(
    private val type: SeeAllType,
    private val repository: HistoryVerseRepository
) : BaseViewModel<SeeAllUIState, SeeAllUIEffect>(SeeAllUIState()) {

    init {
        updateState { it.copy(type = type) }
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        when (state.value.type) {
            SeeAllType.Artifacts -> getArtifacts()
            SeeAllType.Museums -> getMuseums()
            SeeAllType.NoThing -> TODO()
        }
    }

    private fun getArtifacts() {
        tryToExecute(repository::getArtifacts, ::onSuccessArtifact, ::onError)
    }

    private fun onSuccessArtifact(artifacts: List<Artifact>) {
        updateState { it.copy(artifacts = artifacts.toArtifactUiState(), isLoading = false) }
    }

    private fun getMuseums() {
        tryToExecute(
            repository::getMuseums,
            ::onSuccessMuseums,
            ::onError
        )
    }
    private fun onSuccessMuseums(museums: List<Museum>) {
        updateState { it.copy(museums = museums.toMuseumUiState(), isLoading = false) }
    }

    private fun onError() {
        updateState { SeeAllUIState(isError = true, isLoading = false) }
        sendNewEffect(SeeAllUIEffect.SeeAllError)
    }

}