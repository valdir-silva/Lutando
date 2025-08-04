package com.alunando.lutando.data.local

import com.alunando.lutando.domain.model.User

/**
 * Dados iniciais para popular o banco de dados.
 */
object InitialData {

    /**
     * Usuário padrão para a POC.
     */
    val defaultUser = User(
        id = 1,
        name = "Valdir Silva",
        email = "valdir@alunando.com"
    )
} 