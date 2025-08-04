package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.MartialArt
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório de modalidades de artes marciais.
 */
interface MartialArtRepository {

    fun getAllMartialArts(): Flow<List<MartialArt>>

    suspend fun getMartialArtById(id: String): MartialArt?

    suspend fun searchMartialArts(query: String): List<MartialArt>

    suspend fun insertMartialArt(martialArt: MartialArt): String

    suspend fun updateMartialArt(martialArt: MartialArt)

    suspend fun deleteMartialArt(martialArt: MartialArt)

    suspend fun deleteMartialArtById(id: String)
} 