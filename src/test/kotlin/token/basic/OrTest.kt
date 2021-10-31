package token.basic

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token
import token.Token.TokenMatchResult

internal class OrTest {
    private val firstToken = mock<Token>()
    private val secondToken = mock<Token>()

    @AfterEach
    fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(firstToken)
        verifyNoMoreInteractions(secondToken)
    }

    @Test
    fun `Or token match true on first token passed`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(true, input)
        }

        assertEquals(TokenMatchResult(true, "abacaba"), Or(firstToken, secondToken).match("abacaba"))
        assertEquals(TokenMatchResult(true, "caba"), Or(firstToken, secondToken).match("caba"))

        verify(firstToken, times(2)).match
    }

    @Test
    fun `Or token match true on second token passed`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(true, input)
        }.thenReturn { input ->
            TokenMatchResult(false, input)
        }

        whenever(secondToken.match).thenReturn { input ->
            TokenMatchResult(true, "~")
        }

        assertEquals(TokenMatchResult(true, "abacaba"), Or(firstToken, secondToken).match("abacaba"))
        assertEquals(TokenMatchResult(true, "~"), Or(firstToken, secondToken).match("caba"))

        verify(firstToken, times(2)).match
        verify(secondToken, times(1)).match
    }

    @Test
    fun `Or token match false on both tokens filed`() {
        whenever(firstToken.match).thenReturn {
            TokenMatchResult(false, "")
        }

        whenever(secondToken.match).thenReturn {
            TokenMatchResult(false, "")
        }

        assertEquals(TokenMatchResult(false, ""), Or(firstToken, secondToken).match("abacaba"))

        verify(firstToken).match
        verify(secondToken).match
    }
}