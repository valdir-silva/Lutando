package com.example.lutando.domain.model

/**
 * Modelo que representa um usuário do aplicativo Lutando.
 * 
 * @property id Identificador único do usuário
 * @property name Nome completo do usuário
 * @property email Email do usuário (opcional para POC)
 * @property createdAt Data de criação do perfil
 * @property updatedAt Data da última atualização do perfil
 */
data class User(
    val id: Long = 0,
    val name: String,
    val email: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) 