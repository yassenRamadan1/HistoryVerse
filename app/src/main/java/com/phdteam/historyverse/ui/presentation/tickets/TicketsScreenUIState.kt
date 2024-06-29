package com.phdteam.historyverse.ui.presentation.tickets

import com.phdteam.historyverse.ui.presentation.ticket.Ticket

data class TicketsScreenUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val tickets: List<Ticket> = FakeTickets,
)

val FakeTickets = listOf(
    Ticket(
        ticketId = 1,
        museumName = "Egyptian Museum",
        visitorName = "Abdelrahman",
        locationName = "Cairo",
        ticketNumber = "123456",
        visitDate = "01/07/2024",
        ticketType = "Adult"
    ),
    Ticket(
        ticketId = 2,
        museumName = "Alexandria National Museum",
        visitorName = "Abdelrahman",
        locationName = "Alexandria",
        ticketNumber = "654321",
        visitDate = "02/07/2024",
        ticketType = "Child"
    ),
    Ticket(
        ticketId = 3,
        museumName = "Luxor Museum",
        visitorName = "Abdelrahman",
        locationName = "Luxor",
        ticketNumber = "112233",
        visitDate = "03/07/2024",
        ticketType = "Adult"
    ),
    Ticket(
        ticketId = 4,
        museumName = "Museum of Islamic Art",
        visitorName = "Abdelrahman",
        locationName = "Cairo",
        ticketNumber = "445566",
        visitDate = "04/07/2024",
        ticketType = "Child"
    ),
    Ticket(
        ticketId = 5,
        museumName = "Coptic Museum",
        visitorName = "Abdelrahman",
        locationName = "Cairo",
        ticketNumber = "778899",
        visitDate = "05/07/2024",
        ticketType = "Adult"
    ),
    Ticket(
        ticketId = 6,
        museumName = "Nubian Museum",
        visitorName = "Abdelrahman",
        locationName = "Aswan",
        ticketNumber = "990011",
        visitDate = "06/07/2024",
        ticketType = "Child"
    ),
    Ticket(
        ticketId = 7,
        museumName = "Gayer-Anderson Museum",
        visitorName = "Abdelrahman",
        locationName = "Cairo",
        ticketNumber = "223344",
        visitDate = "07/07/2024",
        ticketType = "Adult"
    ),
    Ticket(
        ticketId = 8,
        museumName = "National Museum of Egyptian Civilization",
        visitorName = "Abdelrahman",
        locationName = "Cairo",
        ticketNumber = "556677",
        visitDate = "08/07/2024",
        ticketType = "Child"
    ),
    Ticket(
        ticketId = 9,
        museumName = "El Alamein War Museum",
        visitorName = "Abdelrahman",
        locationName = "El Alamein",
        ticketNumber = "889900",
        visitDate = "09/07/2024",
        ticketType = "Adult"
    ),
    Ticket(
        ticketId = 10,
        museumName = "Kom Oshim Museum",
        visitorName = "Abdelrahman",
        locationName = "Faiyum",
        ticketNumber = "334455",
        visitDate = "10/07/2024",
        ticketType = "Child"
    )
)

