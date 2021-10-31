package token.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token

internal class FalseTest {
    @Test
    fun `False token always match to false`() {
        assertEquals(Token.TokenMatchResult(false, ""), False.match(""))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), False.match("abacaba"))
        assertEquals(Token.TokenMatchResult(false, "      \t"), False.match("      \t"))
    }
}