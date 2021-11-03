package pattern

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token

internal class PackageTest {

    @Test
    fun `Package pattern parser parse true on correct package pattern input`() {
        val patternParserParseResult = Package.parse("a.b.c")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("b.c", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "b.c.qqq"), patternParserParseResult.token.match("a.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "c.b.c.qqq"), patternParserParseResult.token.match("c.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "a"), patternParserParseResult.token.match("a"))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
    }

    @Test
    fun `Package pattern parser parse true on incorrect package pattern input`() {
        val patternParserParseResult = Package.parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(false, "a.b.c.qqq"), patternParserParseResult.token.match("a.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "c.b.c.qqq"), patternParserParseResult.token.match("c.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "a"), patternParserParseResult.token.match("a"))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
    }

    @Test
    fun `Package pattern parser parse true on empty pattern input`() {
        val patternParserParseResult = Package.parse("")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(false, "a.b.c.qqq"), patternParserParseResult.token.match("a.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "c.b.c.qqq"), patternParserParseResult.token.match("c.b.c.qqq"))
        assertEquals(Token.TokenMatchResult(false, "a"), patternParserParseResult.token.match("a"))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
    }
}