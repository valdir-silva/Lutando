package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.Academy
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório de Academias/Dojos.
 */
interface AcademyRepository {

    fun getAllAcademies(): Flow<List<Academy>>

    suspend fun getAcademyById(id: String): Academy?

    suspend fun insertAcademy(academy: Academy): String

    suspend fun updateAcademy(academy: Academy)

    suspend fun deleteAcademy(academy: Academy)

    suspend fun deleteAcademyById(id: String)
}