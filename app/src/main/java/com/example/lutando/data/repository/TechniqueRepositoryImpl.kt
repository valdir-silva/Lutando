package com.example.lutando.data.repository

import com.example.lutando.data.local.TechniqueDao
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.TechniqueRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementação do repositório de técnicas.
 */
class TechniqueRepositoryImpl(
    private val techniqueDao: TechniqueDao
) : TechniqueRepository {
    
    override fun getTechniquesByMartialArt(martialArtId: Long): Flow<List<Technique>> {
        return techniqueDao.getTechniquesByMartialArt(martialArtId)
    }
    
    override suspend fun getTechniqueById(id: Long): Technique? {
        return techniqueDao.getTechniqueById(id)
    }
    
    override suspend fun searchTechniques(martialArtId: Long, query: String): List<Technique> {
        return techniqueDao.searchTechniques(martialArtId, query)
    }
    
    override suspend fun insertTechnique(technique: Technique): Long {
        return techniqueDao.insertTechnique(technique)
    }
    
    override suspend fun updateTechnique(technique: Technique) {
        techniqueDao.updateTechnique(technique)
    }
    
    override suspend fun deleteTechnique(technique: Technique) {
        techniqueDao.deleteTechnique(technique)
    }
    
    override suspend fun deleteTechniqueById(id: Long) {
        techniqueDao.deleteTechniqueById(id)
    }
    
    override suspend fun deleteTechniquesByMartialArt(martialArtId: Long) {
        techniqueDao.deleteTechniquesByMartialArt(martialArtId)
    }
} 