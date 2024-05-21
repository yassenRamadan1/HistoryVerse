package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.data.entity.Mentor
import com.phdteam.historyverse.data.entity.Subject
import com.phdteam.historyverse.data.entity.University
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

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
            repository::getMentors,
            ::onSuccessMentors,
            ::onError
        )
    }

    private fun onSuccessMentors(mentor: List<Mentor>) {
        updateState { it.copy(mentors = mentor.toUiState(), isLoading = false) }
    }

    private fun getSubjects() {
        tryToExecute(
            repository::getSubject,
            ::onSuccessSubject,
            ::onError
        )
    }

    private fun onSuccessSubject(subjects: List<Subject>) {
        updateState { it.copy(subjects = subjects.take(6).toSubjectUiState(), isLoading = false) }
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
                university = universities.take(6).toUniversityUiState(), isLoading = false
            )
        }
    }

    private fun onError() {
        updateState { HomeUIState(isError = true, isLoading = false) }
        sendNewEffect(HomeUIEffect.HomeError)
    }


}

