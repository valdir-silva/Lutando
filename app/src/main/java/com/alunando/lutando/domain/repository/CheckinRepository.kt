package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.util.Resource
import kotlinx.coroutines.flow.Flow

interface CheckinRepository {
    suspend fun addCheckin(checkin: Checkin): Resource<Unit>
    fun getCheckinsByAthlete(athleteId: String): Flow<Resource<List<Checkin>>>
    fun getCheckinsByAcademy(academyId: String): Flow<Resource<List<Checkin>>>
}
