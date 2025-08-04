package com.alunando.lutando.domain.model

/**
 * Modelo que representa uma modalidade de arte marcial.
 *
 * @property id Identificador único da modalidade, gerado pelo Firestore
 * @property name Nome da modalidade (ex: Jiu-Jitsu, Muay Thai, Boxe)
 * @property description Descrição da modalidade
 * @property color Cor associada à modalidade (para UI)
 * @property createdAt Data de criação da modalidade
 * @property updatedAt Data da última atualização da modalidade
 */
data class MartialArt(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    val imageUrl: String? = null,
    var creatorUid: String? = null,
    val color: String? = null
) 