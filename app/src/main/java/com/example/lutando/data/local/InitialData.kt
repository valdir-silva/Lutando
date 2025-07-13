package com.example.lutando.data.local

import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.model.User

/**
 * Dados iniciais para popular o banco de dados.
 */
object InitialData {
    
    /**
     * Lista de modalidades de artes marciais iniciais.
     */
    val martialArts = listOf(
        MartialArt(
            id = 1,
            name = "Jiu-Jitsu Brasileiro",
            description = "Arte marcial focada em luta no chão e finalizações",
            color = "#FF6200EE"
        ),
        MartialArt(
            id = 2,
            name = "Muay Thai",
            description = "Arte marcial tailandesa conhecida como 'a arte das oito armas'",
            color = "#FFD32F2F"
        ),
        MartialArt(
            id = 3,
            name = "Boxe",
            description = "Esporte de combate que utiliza apenas os punhos",
            color = "#FF1976D2"
        ),
        MartialArt(
            id = 4,
            name = "Karatê",
            description = "Arte marcial japonesa que utiliza golpes com mãos e pés",
            color = "#FF388E3C"
        ),
        MartialArt(
            id = 5,
            name = "Taekwondo",
            description = "Arte marcial coreana conhecida pelos chutes espetaculares",
            color = "#FFF57C00"
        ),
        MartialArt(
            id = 6,
            name = "Judo",
            description = "Arte marcial japonesa focada em projeções e imobilizações",
            color = "#FF7B1FA2"
        ),
        MartialArt(
            id = 7,
            name = "Kung Fu",
            description = "Arte marcial chinesa com diversos estilos e técnicas",
            color = "#FFD81B60"
        ),
        MartialArt(
            id = 8,
            name = "Capoeira",
            description = "Arte marcial brasileira que combina luta, dança e música",
            color = "#FF388E3C"
        )
    )
    
    /**
     * Usuário padrão para a POC.
     */
    val defaultUser = User(
        id = 1,
        name = "Valdir Silva",
        email = "valdir@example.com"
    )
} 