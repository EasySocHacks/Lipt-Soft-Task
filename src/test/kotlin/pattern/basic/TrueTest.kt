package pattern.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class TrueTest {
    @Test
    fun `True pattern parser always parse true`() {
        val patternParserParseResult = True.parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "qqqqqqq"), patternParserParseResult.token.match("qqqqqqq"))
    }
}