package com.example.lutando.data.local

import androidx.room.*
import com.example.lutando.domain.model.MartialArt
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações com modalidades de artes marciais no banco de dados.
 */
@Dao
interface MartialArtDao {
    
    @Query("SELECT * FROM martial_arts ORDER BY name ASC")
    fun getAllMartialArts(): Flow<List<MartialArt>>
    
    @Query("SELECT * FROM martial_arts WHERE id = :id")
    suspend fun getMartialArtById(id: Long): MartialArt?
    
    @Query("SELECT * FROM martial_arts WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    suspend fun searchMartialArts(query: String): List<MartialArt>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMartialArt(martialArt: MartialArt): Long
    
    @Update
    suspend fun updateMartialArt(martialArt: MartialArt)
    
    @Delete
    suspend fun deleteMartialArt(martialArt: MartialArt)
    
    @Query("DELETE FROM martial_arts WHERE id = :id")
    suspend fun deleteMartialArtById(id: Long)
} 