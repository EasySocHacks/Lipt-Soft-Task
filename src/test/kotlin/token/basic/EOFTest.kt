package token.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class EOFTest {
    @Test
    fun `EOF token match empty input to true`() {
        assertEquals(TokenMatchResult(true, ""), EOF.match(""))
    }

    @Test
    fun `EOF token match not empty input to false`() {
        assertEquals(TokenMatchResult(false, "    "), EOF.match("    "))
        assertEquals(TokenMatchResult(false, "abacaba"), EOF.match("abacaba"))
        assertEquals(TokenMatchResult(false, "\t"), EOF.match("\t"))
    }
}