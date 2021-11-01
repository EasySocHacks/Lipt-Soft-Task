package token.basic

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import token.Token
import token.Token.TokenMatchResult

internal class AndTest {
    private val firstToken = mock<Token>()
    private val secondToken = mock<Token>()

    @AfterEach
    fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(firstToken)
        verifyNoMoreInteractions(secondToken)
    }

    @Test
    fun `And token match false on first token fail`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(false, input)
        }

        assertEquals(TokenMatchResult(false, ""), And(firstToken, secondToken).match(""))
        assertEquals(TokenMatchResult(false, "abacaba"), And(firstToken, secondToken).match("abacaba"))
        assertEquals(TokenMatchResult(false, "       \t"), And(firstToken, secondToken).match("       \t"))

        verify(firstToken, times(3)).match
    }

    @Test
    fun `And token match false on first token passed and second token fail`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(true, input)
        }

        whenever(secondToken.match).thenReturn { input ->
            TokenMatchResult(false, input)
        }

        assertEquals(TokenMatchResult(false, ""), And(firstToken, secondToken).match(""))
        assertEquals(TokenMatchResult(false, "abacaba"), And(firstToken, secondToken).match("abacaba"))
        assertEquals(TokenMatchResult(false, "       \t"), And(firstToken, secondToken).match("       \t"))

        verify(firstToken, times(3)).match
        verify(secondToken, times(3)).match
    }

    @Test
    fun `And token match true on first token passed and second token passed`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(true, input)
        }

        whenever(secondToken.match).thenReturn { input ->
            TokenMatchResult(true, "A")
        }

        assertEquals(TokenMatchResult(true, "A"), And(firstToken, secondToken).match(""))
        assertEquals(TokenMatchResult(true, "A"), And(firstToken, secondToken).match("abacaba"))
        assertEquals(TokenMatchResult(true, "A"), And(firstToken, secondToken).match("       \t"))

        verify(firstToken, times(3)).match
        verify(secondToken, times(3)).match
    }
}