package com.example.lutando.data.local

import androidx.room.TypeConverter
import com.example.lutando.domain.model.MediaFile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Conversores para o banco de dados Room.
 */
class Converters {

    private val gson = Gson()

    /**
     * Converte lista de MediaFile para JSON string.
     */
    @TypeConverter
    fun fromMediaFileList(value: List<MediaFile>?): String {
        return gson.toJson(value ?: emptyList<MediaFile>())
    }

    /**
     * Converte JSON string para lista de MediaFile.
     */
    @TypeConverter
    fun toMediaFileList(value: String): List<MediaFile> {
        return try {
            val listType = object : TypeToken<List<MediaFile>>() {}.type
            gson.fromJson(value, listType) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
} 