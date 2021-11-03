package pattern.basic

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import pattern.PatternParser
import token.Token
import token.basic.And
import token.basic.True

internal class ManyOrZeroTest {
    private val token = mock<Token>()

    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(token, patternParser)
    }

    @Test
    fun `ManyOrZero pattern parser parse true on internal pattern parser parse true`() {
        whenever(token.match).thenReturn(True.match)
        whenever(token.then(any())).thenReturn(And(token, True))

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

        val patternParserParseResult = ManyOrZero(patternParser).parse("aaaaaaaaaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("ba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(token).match
        verify(token, times(8)).then(any())
        verify(patternParser, times(10)).parse
    }

    @Test
    fun `Many pattern parser parse true on internal pattern parser parse false`() {
        whenever(patternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                false,
                token,
                pattern
            )
        }

        val patternParserParseResult = ManyOrZero(patternParser).parse("aaaaaaaaaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("aaaaaaaaaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(patternParser).parse
    }
}