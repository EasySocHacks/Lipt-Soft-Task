package token

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CharacterTest {
    @Test
    fun `Character token match true on first character of input is match token's character`() {
        assertEquals(Token.TokenMatchResult(true, "bacaba"), Character('a').match("abacaba"))
        assertEquals(Token.TokenMatchResult(true, ""), Character('a').match("a"))
        assertEquals(Token.TokenMatchResult(true, "aaa"), Character('a').match("aaaa"))
    }

    @Test
    fun `Character token match false on first character of input isn't match token's character`() {
        assertEquals(Token.TokenMatchResult(false, "bacaba"), Character('a').match("bacaba"))
        assertEquals(Token.TokenMatchResult(false, "b"), Character('a').match("b"))
        assertEquals(Token.TokenMatchResult(false, "bbbb"), Character('a').match("bbbb"))
        assertEquals(Token.TokenMatchResult(false, ""), Character('a').match(""))
    }
}