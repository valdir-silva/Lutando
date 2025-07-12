package com.example.lutando.domain.model

/**
 * Modelo que representa uma modalidade de arte marcial.
 * 
 * @property id Identificador único da modalidade
 * @property name Nome da modalidade (ex: Jiu-Jitsu, Muay Thai, Boxe)
 * @property description Descrição da modalidade
 * @property color Cor associada à modalidade (para UI)
 * @property createdAt Data de criação da modalidade
 * @property updatedAt Data da última atualização da modalidade
 */
data class MartialArt(
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val color: String = "#FF6200EE", // Cor padrão Material Design
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) 