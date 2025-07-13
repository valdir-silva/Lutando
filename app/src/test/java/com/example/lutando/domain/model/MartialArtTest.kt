package com.example.lutando.domain.model

import org.junit.Test
import org.junit.Assert.*

class MartialArtTest {

    @Test
    fun `deve criar MartialArt com valores padrão`() {
        // Given
        val name = "Jiu-Jitsu"
        
        // When
        val martialArt = MartialArt(name = name)
        
        // Then
        assertEquals(0L, martialArt.id)
        assertEquals(name, martialArt.name)
        assertEquals("", martialArt.description)
        assertEquals("#FF6200EE", martialArt.color)
        assertTrue(martialArt.createdAt > 0)
        assertTrue(martialArt.updatedAt > 0)
    }

    @Test
    fun `deve criar MartialArt com todos os valores customizados`() {
        // Given
        val id = 1L
        val name = "Muay Thai"
        val description = "Arte marcial tailandesa"
        val color = "#FF0000"
        val createdAt = 1000L
        val updatedAt = 2000L
        
        // When
        val martialArt = MartialArt(
            id = id,
            name = name,
            description = description,
            color = color,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
        
        // Then
        assertEquals(id, martialArt.id)
        assertEquals(name, martialArt.name)
        assertEquals(description, martialArt.description)
        assertEquals(color, martialArt.color)
        assertEquals(createdAt, martialArt.createdAt)
        assertEquals(updatedAt, martialArt.updatedAt)
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
        assertEquals(original.createdAt, copy.createdAt)
        assertEquals(original.updatedAt, copy.updatedAt)
    }
} 