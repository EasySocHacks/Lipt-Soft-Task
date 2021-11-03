package pattern

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token

internal class EndOfClassNameTest {

    @Test
    fun `EndOfClassName pattern parser parse true on blank character`() {
        val patternParserParseResult = EndOfClassName.parse(" ")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, ""), patternParserParseResult.token.match(""))
        assertEquals(Token.TokenMatchResult(false, " "), patternParserParseResult.token.match(" "))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
    }

    @Test
    fun `EndOfClassName pattern parser parse false on non-blank character`() {
        val patternParserParseResult = EndOfClassName.parse("a")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("a", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
        assertEquals(Token.TokenMatchResult(false, " "), patternParserParseResult.token.match(" "))
        assertEquals(Token.TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
    }
}