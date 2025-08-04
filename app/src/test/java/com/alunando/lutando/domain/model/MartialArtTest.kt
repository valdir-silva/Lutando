package com.alunando.lutando.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MartialArtTest {

    @Test
    fun `deve criar MartialArt com valores padrão`() {
        // Given
        val name = "Jiu-Jitsu"

        // When
        val martialArt = MartialArt(name = name)

        // Then
        assertEquals("", martialArt.id)
        assertEquals(name, martialArt.name)
        assertEquals("", martialArt.description)
        assertEquals(null, martialArt.color)
    }

    @Test
    fun `deve criar MartialArt com todos os valores customizados`() {
        // Given
        val id = "1"
        val name = "Muay Thai"
        val description = "Arte marcial tailandesa"
        val color = "#FF0000"

        // When
        val martialArt = MartialArt(
            id = id,
            name = name,
            description = description,
            color = color
        )

        // Then
        assertEquals(id, martialArt.id)
        assertEquals(name, martialArt.name)
        assertEquals(description, martialArt.description)
        assertEquals(color, martialArt.color)
    }

    @Test
    fun `deve comparar MartialArt corretamente`() {
        // Given
        val martialArt1 = MartialArt(name = "Boxe")
        val martialArt2 = MartialArt(name = "Boxe")
        val martialArt3 = MartialArt(name = "Karatê")

        // When & Then
        assertEquals(martialArt1, martialArt2)
        assertNotEquals(martialArt1, martialArt3)
    }

    @Test
    fun `deve copiar MartialArt com novos valores`() {
        // Given
        val original = MartialArt(name = "Judo")

        // When
        val copy = original.copy(
            name = "Aikido",
            description = "Arte marcial japonesa"
        )

        // Then
        assertEquals(original.id, copy.id)
        assertEquals("Aikido", copy.name)
        assertEquals("Arte marcial japonesa", copy.description)
        assertEquals(original.color, copy.color)
    }
} 