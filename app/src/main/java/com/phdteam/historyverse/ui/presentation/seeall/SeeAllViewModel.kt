package com.phdteam.historyverse.ui.presentation.seeall

import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.toUiState
import com.phdteam.historyverse.ui.presentation.home.toUniversityUiState

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
            SeeAllType.Mentors -> getMentors()
            SeeAllType.Universities -> getUniversities()
            SeeAllType.NoThing -> TODO()
        }
    }

    private fun getMentors() {
        tryToExecute(repository::getMentors, ::onSuccessMentors, ::onError)
    }

    private fun onSuccessMentors(mentor: List<Mentor>) {
        updateState { it.copy(mentors = mentor.toUiState(), isLoading = false) }
    }

    private fun getUniversities() {
        tryToExecute(repository::getUniversities, ::onSuccessUniversity, ::onError)
    }

    private fun onSuccessUniversity(universities: List<University>) {
        updateState {
            it.copy(universities = universities.toUniversityUiState(), isLoading = false)
        }
    }

    private fun onError() {
        updateState { SeeAllUIState(isError = true, isLoading = false) }
        sendNewEffect(SeeAllUIEffect.SeeAllError)
    }

}