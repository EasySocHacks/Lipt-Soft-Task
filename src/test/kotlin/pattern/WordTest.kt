package pattern

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class WordTest {
    @Test
    fun `LowercaseWord pattern parser returns true on lowercase input pattern`() {
        val patternParserParseResult = LowercaseWord.parse("abaC")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("C", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "caba"), patternParserParseResult.token.match("abacaba"))
        assertEquals(TokenMatchResult(true, ""), patternParserParseResult.token.match("aba"))
        assertEquals(TokenMatchResult(false, "Aba"), patternParserParseResult.token.match("Aba"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
    }

    @Test
    fun `LowercaseWord pattern parser returns false on non-lowercase input pattern`() {
        val patternParserParseResult = LowercaseWord.parse("Caba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("Caba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "abacaba"), patternParserParseResult.token.match("abacaba"))
        assertEquals(TokenMatchResult(false, "aba"), patternParserParseResult.token.match("aba"))
        assertEquals(TokenMatchResult(false, "Aba"), patternParserParseResult.token.match("Aba"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
    }

    @Test
    fun `CamelcaseWord pattern parser returns true on camelcase input pattern`() {
        val patternParserParseResult = CamelcaseWord.parse("AbaCaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("Caba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "Caba"), patternParserParseResult.token.match("AbaCaba"))
        assertEquals(TokenMatchResult(true, "CababadjBASJDb"), patternParserParseResult.token.match("AbaCababadjBASJDb"))
        assertEquals(TokenMatchResult(true, ""), patternParserParseResult.token.match("Aba"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
    }

    @Test
    fun `CamelcaseWord pattern parser returns false on non-camelcase input pattern`() {
        val patternParserParseResult = CamelcaseWord.parse("baCaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("baCaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "AbaCaba"), patternParserParseResult.token.match("AbaCaba"))
        assertEquals(TokenMatchResult(false, "AbaCababadjBASJDb"), patternParserParseResult.token.match("AbaCababadjBASJDb"))
        assertEquals(TokenMatchResult(false, "Aba"), patternParserParseResult.token.match("Aba"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
    }
}