package token.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class SkipTest {
    @Test
    fun `Skip token match true on non-empty input`() {
        assertEquals(TokenMatchResult(true, ""), Skip.match(" "))
        assertEquals(TokenMatchResult(true, "bacaba"), Skip.match("abacaba"))
        assertEquals(TokenMatchResult(true, ""), Skip.match("\t"))
    }

    @Test
    fun `Skip token match false on empty input`() {
        assertEquals(TokenMatchResult(false, ""), Skip.match(""))
    }
}