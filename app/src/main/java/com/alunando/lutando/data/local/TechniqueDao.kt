package com.alunando.lutando.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alunando.lutando.domain.model.Technique
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações com técnicas no banco de dados.
 */
@Dao
interface TechniqueDao {

    @Query("SELECT * FROM techniques WHERE martialArtId = :martialArtId ORDER BY name ASC")
    fun getTechniquesByMartialArt(martialArtId: Long): Flow<List<Technique>>

    @Query("SELECT * FROM techniques WHERE id = :id")
    suspend fun getTechniqueById(id: Long): Technique?

    @Query("SELECT * FROM techniques WHERE martialArtId = :martialArtId AND name LIKE '%' || :query || '%' ORDER BY name ASC")
    suspend fun searchTechniques(martialArtId: Long, query: String): List<Technique>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTechnique(technique: Technique): Long

    @Update
    suspend fun updateTechnique(technique: Technique)

    @Delete
    suspend fun deleteTechnique(technique: Technique)

    @Query("DELETE FROM techniques WHERE id = :id")
    suspend fun deleteTechniqueById(id: Long)

    @Query("DELETE FROM techniques WHERE martialArtId = :martialArtId")
    suspend fun deleteTechniquesByMartialArt(martialArtId: Long)
} 