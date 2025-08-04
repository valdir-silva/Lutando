package com.alunando.lutando.domain.model

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
data class Comment(
    val id: String = "",
    val techniqueId: String,
    val author: String,
    val text: String,
    val createdAt: String = "",
    val updatedAt: String = ""
) 