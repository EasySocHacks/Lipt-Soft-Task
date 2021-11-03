package pattern.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token

internal class FalseTest {
    @Test
    fun `False pattern parser always parse false`() {
        val patternParserParseResult = False.parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(false, "qqqqqqq"), patternParserParseResult.token.match("qqqqqqq"))
    }
}