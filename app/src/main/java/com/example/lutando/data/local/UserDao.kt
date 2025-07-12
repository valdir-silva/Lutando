package com.example.lutando.data.local

// import androidx.room.*
// import com.example.lutando.domain.model.User
// import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações com usuários no banco de dados.
 * TEMPORARIAMENTE COMENTADO - será reativado quando implementar Room
 */
// @Dao
// interface UserDao {
    
//     @Query("SELECT * FROM users LIMIT 1")
//     fun getCurrentUser(): Flow<User?>
    
//     @Query("SELECT * FROM users WHERE id = :id")
//     suspend fun getUserById(id: Long): User?
    
//     @Insert(onConflict = OnConflictStrategy.REPLACE)
//     suspend fun insertUser(user: User): Long
    
//     @Update
//     suspend fun updateUser(user: User)
    
//     @Delete
//     suspend fun deleteUser(user: User)
    
//     @Query("DELETE FROM users WHERE id = :id")
//     suspend fun deleteUserById(id: Long)
// } 