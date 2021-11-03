package pattern

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import token.Token.TokenMatchResult

internal class WildcardUntilTest {
    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(patternParser)
    }

    @Test
    fun `WildcardUntil returns true on wildcard character`() {
        whenever(patternParser.parse).thenReturn { pattern ->
            Character('a').parse(pattern)
        }

        val patternParserParseResult = WildcardUntil(patternParser).parse("*ab")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("b", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "bbb"), patternParserParseResult.token.match("qqqqqabbb"))
        assertEquals(TokenMatchResult(false, "qqqqqbbb"), patternParserParseResult.token.match("qqqqqbbb"))

        verify(patternParser).parse
    }

    @Test
    fun `WildcardUntil returns false on no wildcard character`() {
        val patternParserParseResult = WildcardUntil(patternParser).parse("ab")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("ab", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "qqqqqabbb"), patternParserParseResult.token.match("qqqqqabbb"))
        assertEquals(TokenMatchResult(false, "qqqqqbbb"), patternParserParseResult.token.match("qqqqqbbb"))
    }
}