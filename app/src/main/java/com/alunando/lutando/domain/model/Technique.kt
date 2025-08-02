package com.alunando.lutando.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Modelo que representa uma técnica de arte marcial.
 *
 * @property id Identificador único da técnica
 * @property name Nome da técnica
 * @property description Descrição detalhada da técnica
 * @property martialArtId ID da modalidade de arte marcial
 * @property hasVideo Indica se a técnica possui vídeo
 * @property hasPhoto Indica se a técnica possui foto
 * @property hasAudio Indica se a técnica possui áudio
 * @property videoPath Caminho do arquivo de vídeo
 * @property photoPath Caminho do arquivo de foto
 * @property audioPath Caminho do arquivo de áudio
 * @property createdAt Data de criação da técnica
 * @property updatedAt Data da última atualização da técnica
 */
@Entity(tableName = "techniques")
data class Technique(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val martialArtId: Long,
    val hasVideo: Boolean = false,
    val hasPhoto: Boolean = false,
    val hasAudio: Boolean = false,
    val videoPath: String = "",
    val photoPath: String = "",
    val audioPath: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
)

/**
 * Classe que representa um arquivo de mídia associado a uma técnica.
 *
 * @property id Identificador único do arquivo
 * @property type Tipo de mídia (PHOTO, VIDEO, AUDIO)
 * @property uri URI do arquivo no dispositivo
 * @property description Descrição opcional do arquivo
 * @property createdAt Data de criação do arquivo
 */
data class MediaFile(
    val id: String,
    val type: MediaType,
    val uri: String,
    val description: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Enum que define os tipos de mídia suportados.
 */
enum class MediaType {
    PHOTO,
    VIDEO,
    AUDIO
} 