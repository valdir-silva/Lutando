package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.Technique
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório de técnicas.
 */
interface TechniqueRepository {

    fun getTechniquesByMartialArt(martialArtId: String): Flow<List<Technique>>

    suspend fun getTechniqueById(id: String): Technique?

    suspend fun searchTechniques(martialArtId: String, query: String): List<Technique>

    suspend fun insertTechnique(technique: Technique): String

    suspend fun updateTechnique(technique: Technique)

    suspend fun deleteTechnique(technique: Technique)

    suspend fun deleteTechniqueById(id: String)

    suspend fun deleteTechniquesByMartialArt(martialArtId: String)
} 