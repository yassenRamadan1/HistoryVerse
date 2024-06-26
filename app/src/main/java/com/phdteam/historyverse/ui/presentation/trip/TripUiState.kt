package com.phdteam.historyverse.ui.presentation.trip

import com.phdteam.historyverse.data.network.model.Trip
import com.phdteam.historyverse.data.network.model.TripChoice
import com.phdteam.historyverse.ui.presentation.home.MuseumUiState

data class TripUiState(
    val trip: List<Trip.TripPlan?>? = emptyList(),
    val museums: List<MuseumUiState> = emptyList(),
    val targetCities : List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val egyptGovernments: List<String> = listOf(
        "Alexandria",
        "Aswan",
        "ASYUT",
        "Beni Suef",
        "Cairo",
        "FAYOUM",
        "Giza",
        "Sharm El-Sheikh",
        "LUXOR",
        "Nubian Textile Fragment",
        "Port Said",
        "QALUBIA",
        "SHARQIA",
        "New Valley",
        "Rashid ",
        "Suez",
        "MANSOURA",
        "MATROUH",
        "Hurghada",
        "MINYA"
    ),
    val tripChoice: TripChoice = TripChoice(
        cities = listOf("Cairo", "Alexandria"),
        startLocation = "current location"
    )
)
