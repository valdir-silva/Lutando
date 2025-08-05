package com.alunando.lutando.domain.model

/**
 * Modelo que representa uma Academia/Dojo.
 *
 * @property id Identificador único da academia, gerado pelo Firestore
 * @property name Nome da academia
 * @property address Endereço da academia
 * @property phone Telefone de contato da academia
 * @property email Email de contato da academia
 * @property creatorUid UID do usuário que criou a academia
 * @property createdAt Data de criação da academia
 * @property updatedAt Data da última atualização da academia
 */
data class Academy(
    var id: String = "",
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    var creatorUid: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
