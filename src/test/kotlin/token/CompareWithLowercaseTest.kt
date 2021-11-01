package token

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import token.Token.TokenMatchResult

internal class CompareWithLowercaseTest {
    private val token = mock<Token>()

    @AfterEach
    fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(token)
    }

    @Test
    fun `CompareWithLowercaseTest match true on internal token match true`() {
        whenever(token.match).thenReturn { input ->
            when (input) {
                "abacaba" -> TokenMatchResult(
                    true,
                    ""
                )

                else -> TokenMatchResult(
                    false,
                    input
                )
            }
        }

        assertEquals(TokenMatchResult(true, ""), CompareWithLowercase(token).match("abacaba"))
        assertEquals(TokenMatchResult(true, ""), CompareWithLowercase(token).match("ABACABA"))
        assertEquals(TokenMatchResult(true, ""), CompareWithLowercase(token).match("AbAcAbA"))
        assertEquals(TokenMatchResult(true, ""), CompareWithLowercase(token).match("abaCABA"))

        verify(token, times(4)).match
    }
}