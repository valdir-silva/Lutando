package com.example.lutando.data.repository

import com.example.lutando.data.local.MartialArtDao
import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.repository.MartialArtRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementação do repositório de modalidades de artes marciais.
 */
class MartialArtRepositoryImpl(
    private val martialArtDao: MartialArtDao
) : MartialArtRepository {

    override fun getAllMartialArts(): Flow<List<MartialArt>> {
        return martialArtDao.getAllMartialArts()
    }

    override suspend fun getMartialArtById(id: Long): MartialArt? {
        return martialArtDao.getMartialArtById(id)
    }

    override suspend fun searchMartialArts(query: String): List<MartialArt> {
        return martialArtDao.searchMartialArts(query)
    }

    override suspend fun insertMartialArt(martialArt: MartialArt): Long {
        return martialArtDao.insertMartialArt(martialArt)
    }

    override suspend fun updateMartialArt(martialArt: MartialArt) {
        martialArtDao.updateMartialArt(martialArt)
    }

    override suspend fun deleteMartialArt(martialArt: MartialArt) {
        martialArtDao.deleteMartialArt(martialArt)
    }

    override suspend fun deleteMartialArtById(id: Long) {
        martialArtDao.deleteMartialArtById(id)
    }
} 