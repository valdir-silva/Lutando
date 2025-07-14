package com.example.lutando.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Banco de dados Room para o aplicativo Lutando.
 */
@Database(
    entities = [
        User::class,
        MartialArt::class,
        Technique::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LutandoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun martialArtDao(): MartialArtDao
    abstract fun techniqueDao(): TechniqueDao

    companion object {
        @Volatile
        private var INSTANCE: LutandoDatabase? = null

        fun getDatabase(context: Context): LutandoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LutandoDatabase::class.java,
                    "lutando_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    populateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun populateDatabase(database: LutandoDatabase) {
            val userDao = database.userDao()
            val martialArtDao = database.martialArtDao()

            // Inserir usuário padrão
            userDao.insertUser(InitialData.defaultUser)

            // Inserir modalidades iniciais
            InitialData.martialArts.forEach { martialArt ->
                martialArtDao.insertMartialArt(martialArt)
            }
        }
    }
} 