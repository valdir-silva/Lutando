package com.example.lutando.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Modelo que representa um comentário em uma técnica.
 *
 * @property id Identificador único do comentário
 * @property techniqueId ID da técnica associada
 * @property author Nome do autor do comentário
 * @property text Texto do comentário
 * @property createdAt Data de criação do comentário
 * @property updatedAt Data da última atualização do comentário
 */
@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val techniqueId: Long,
    val author: String,
    val text: String,
    val createdAt: String = "",
    val updatedAt: String = ""
) 