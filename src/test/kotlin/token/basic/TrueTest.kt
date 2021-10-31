package token.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult


internal class TrueTest {

    @Test
    fun `True token always match to true`() {
        assertEquals(TokenMatchResult(true, ""), True.match(""))
        assertEquals(TokenMatchResult(true, "abacaba"), True.match("abacaba"))
        assertEquals(TokenMatchResult(true, "      \t"), True.match("      \t"))
    }
}