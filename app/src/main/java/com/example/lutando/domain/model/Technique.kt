package com.example.lutando.domain.model

/**
 * Modelo que representa uma técnica de arte marcial.
 * 
 * @property id Identificador único da técnica
 * @property name Nome da técnica
 * @property description Descrição detalhada da técnica
 * @property martialArtId ID da modalidade de arte marcial
 * @property notes Notas adicionais do usuário
 * @property mediaFiles Lista de arquivos de mídia associados (fotos, vídeos, áudios)
 * @property createdAt Data de criação da técnica
 * @property updatedAt Data da última atualização da técnica
 */
data class Technique(
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val martialArtId: Long,
    val notes: String = "",
    val mediaFiles: String = "", // JSON string com lista de MediaFile
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
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