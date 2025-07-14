package com.example.lutando.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Modelo que representa um usuário do aplicativo Lutando.
 *
 * @property id Identificador único do usuário
 * @property name Nome completo do usuário
 * @property email Email do usuário (opcional para POC)
 * @property createdAt Data de criação do perfil
 * @property updatedAt Data da última atualização do perfil
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) 