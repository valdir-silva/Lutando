package com.alunando.lutando.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class UserTest {

    @Test
    fun `deve criar User com valores padrão`() {
        // Given
        val name = "João Silva"

        // When
        val user = User(name = name)

        // Then
        assertEquals("", user.id)
        assertEquals(name, user.name)
        assertNull(user.email)
        assertTrue(user.createdAt > 0)
        assertTrue(user.updatedAt > 0)
    }

    @Test
    fun `deve criar User com todos os valores customizados`() {
        // Given
        val id = "1"
        val name = "Maria Santos"
        val email = "maria@email.com"
        val createdAt = 1000L
        val updatedAt = 2000L

        // When
        val user = User(
            id = id,
            name = name,
            email = email,
            createdAt = createdAt,
            updatedAt = updatedAt
        )

        // Then
        assertEquals(id, user.id)
        assertEquals(name, user.name)
        assertEquals(email, user.email)
        assertEquals(createdAt, user.createdAt)
        assertEquals(updatedAt, user.updatedAt)
    }

    @Test
    fun `deve criar User com email nulo`() {
        // Given
        val name = "Pedro Costa"

        // When
        val user = User(name = name, email = null)

        // Then
        assertEquals(name, user.name)
        assertNull(user.email)
    }

    @Test
    fun `deve comparar User corretamente`() {
        // Given
        val user1 = User(name = "Ana", email = "ana@email.com")
        val user2 = User(name = "Ana", email = "ana@email.com")
        val user3 = User(name = "Ana", email = "outro@email.com")

        // When & Then
        assertEquals(user1, user2)
        assertNotEquals(user1, user3)
    }

    @Test
    fun `deve copiar User com novos valores`() {
        // Given
        val original = User(name = "Carlos", email = "carlos@email.com")

        // When
        val copy = original.copy(
            name = "Carlos Silva",
            email = "carlos.silva@email.com"
        )

        // Then
        assertEquals(original.id, copy.id)
        assertEquals("Carlos Silva", copy.name)
        assertEquals("carlos.silva@email.com", copy.email)
        assertEquals(original.createdAt, copy.createdAt)
        assertEquals(original.updatedAt, copy.updatedAt)
    }

    @Test
    fun `deve copiar User removendo email`() {
        // Given
        val original = User(name = "Fernanda", email = "fernanda@email.com")

        // When
        val copy = original.copy(email = null)

        // Then
        assertEquals(original.id, copy.id)
        assertEquals(original.name, copy.name)
        assertNull(copy.email)
        assertEquals(original.createdAt, copy.createdAt)
        assertEquals(original.updatedAt, copy.updatedAt)
    }
} 