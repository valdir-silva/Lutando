package com.example.lutando.data.repository

// import com.example.lutando.data.local.UserDao
// import com.example.lutando.domain.model.User
// import com.example.lutando.domain.repository.UserRepository
// import kotlinx.coroutines.flow.Flow

/**
 * Implementação do repositório de usuários.
 * TEMPORARIAMENTE COMENTADO - será reativado quando implementar Room
 */
// class UserRepositoryImpl(
//     private val userDao: UserDao
// ) : UserRepository {
    
//     override fun getCurrentUser(): Flow<User?> {
//         return userDao.getCurrentUser()
//     }
    
//     override suspend fun getUserById(id: Long): User? {
//         return userDao.getUserById(id)
//     }
    
//     override suspend fun insertUser(user: User): Long {
//         return userDao.insertUser(user)
//     }
    
//     override suspend fun updateUser(user: User) {
//         userDao.updateUser(user)
//     }
    
//     override suspend fun deleteUser(user: User) {
//         userDao.deleteUser(user)
//     }
    
//     override suspend fun deleteUserById(id: Long) {
//         userDao.deleteUserById(id)
//     }
// } 