package token

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class SkipUntilTest {
    private val token = mock<Token>()

    @AfterEach
    fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(token)
    }

    @Test
    fun `SkipUntil token match true on internal token match true somewhere inside`() {
        whenever(token.match).thenReturn { input ->
            when (input.first()) {
                'a' -> TokenMatchResult(
                    true,
                    input.substring(1)
                )

                else -> TokenMatchResult(
                    false,
                    input
                )
            }
        }

        assertEquals(TokenMatchResult(true, "bbb"), SkipUntil(token).match("fffabbb"))
        assertEquals(TokenMatchResult(true, ""), SkipUntil(token).match("a"))

        verify(token, times(5)).match
    }
    @Test
    fun `SkipUntil token match false on internal token match false wherever inside`() {
        whenever(token.match).thenReturn { input ->
            when (input.first()) {
                'a' -> TokenMatchResult(
                    true,
                    input.substring(1)
                )

                else -> TokenMatchResult(
                    false,
                    input
                )
            }
        }

        assertEquals(TokenMatchResult(false, ""), SkipUntil(token).match("zczczcdcgfdg"))
        assertEquals(TokenMatchResult(false, ""), SkipUntil(token).match(""))
        assertEquals(TokenMatchResult(false, ""), SkipUntil(token).match(" "))

        verify(token, times(13)).match
    }
}