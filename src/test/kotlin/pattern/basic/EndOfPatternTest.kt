package pattern.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class EndOfPatternTest {
    @Test
    fun `EndOfPattern parse true on empty pattern`() {
        val patternParserParseResult = EndOfPattern.parse("")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, ""), patternParserParseResult.token.match(""))
        assertEquals(TokenMatchResult(true, "aba"), patternParserParseResult.token.match("aba"))
    }

    @Test
    fun `EndOfPattern parse false on non-empty pattern`() {
        val patternParserParseResult = EndOfPattern.parse("abac")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abac", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
        assertEquals(TokenMatchResult(false, "aba"), patternParserParseResult.token.match("aba"))
    }
}