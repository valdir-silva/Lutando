package com.alunando.lutando.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.model.User

/**
 * Banco de dados Room para o aplicativo Lutando.
 */
@Database(
    entities = [
        User::class,
        Technique::class,
        Comment::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LutandoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun techniqueDao(): TechniqueDao
    abstract fun commentDao(): CommentDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS `comments` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `techniqueId` INTEGER NOT NULL,
                        `author` TEXT NOT NULL,
                        `text` TEXT NOT NULL,
                        `createdAt` TEXT NOT NULL,
                        `updatedAt` TEXT NOT NULL,
                        FOREIGN KEY(`techniqueId`) REFERENCES `techniques`(`id`) ON DELETE CASCADE
                    )
                """)
            }
        }
    }
} 