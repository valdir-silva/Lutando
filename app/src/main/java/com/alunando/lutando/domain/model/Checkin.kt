package com.alunando.lutando.domain.model

import com.google.firebase.Timestamp

data class Checkin(
    val id: String = "",
    val athleteId: String = "",
    val academyId: String = "",
    val timestamp: Timestamp = Timestamp.now()
)
