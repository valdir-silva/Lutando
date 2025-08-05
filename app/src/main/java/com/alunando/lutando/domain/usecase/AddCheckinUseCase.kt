package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.repository.CheckinRepository
import com.alunando.lutando.util.Resource

class AddCheckinUseCase(
    private val repository: CheckinRepository
) {
    suspend operator fun invoke(checkin: Checkin): Resource<Unit> {
        return repository.addCheckin(checkin)
    }
}
