package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.repository.CheckinRepository
import com.alunando.lutando.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCheckinsByAthleteUseCase(
    private val repository: CheckinRepository
) {
    operator fun invoke(athleteId: String): Flow<Resource<List<Checkin>>> {
        return repository.getCheckinsByAthlete(athleteId)
    }
}
