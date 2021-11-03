package pattern.basic

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import pattern.PatternParser
import token.Token
import token.basic.True

internal class OneOrZeroTest {
    private val token = mock<Token>()

    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(token, patternParser)
    }

    @Test
    fun `OmeOrZero pattern parser parse true on internal pattern parser parse true`() {
        whenever(token.match).thenReturn(True.match)

        whenever(patternParser.parse).thenReturn { pattern ->
            when {
                pattern.isNotEmpty() && pattern.first() == 'a' -> PatternParser.PatternParserParseResult(
                    true,
                    token,
                    pattern.substring(1)
                )
                else -> PatternParser.PatternParserParseResult(
                    false,
                    token,
                    pattern
                )
            }
        }

        val patternParserParseResult = OneOrZero(patternParser).parse("aaaaaaaaaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("aaaaaaaaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(token).match
        verify(patternParser).parse
    }

    @Test
    fun `OneOrZero pattern parser parse true on internal pattern parser parse false`() {
        whenever(patternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                false,
                token,
                pattern
            )
        }

        val patternParserParseResult = OneOrZero(patternParser).parse("aaaaaaaaaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("aaaaaaaaaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(patternParser).parse
    }
}