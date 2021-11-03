package pattern.basic

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import pattern.PatternParser
import pattern.PatternParser.PatternParserParseResult
import token.Token
import token.Token.TokenMatchResult
import token.basic.And
import token.basic.True

internal class ManyTest {
    private val token = mock<Token>()

    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(token, patternParser)
    }

    @Test
    fun `Many pattern parser parse true on internal pattern parser parse true`() {
        whenever(token.match).thenReturn(True.match)
        whenever(token.then(any())).thenReturn(And(token, True))

        whenever(patternParser.parse).thenReturn { pattern ->
            when {
                pattern.isNotEmpty() && pattern.first() == 'a' -> PatternParserParseResult(
                    true,
                    token,
                    pattern.substring(1)
                )
                else -> PatternParserParseResult(
                    false,
                    token,
                    pattern
                )
            }
        }

        val patternParserParseResult = Many(patternParser).parse("aaaaaaaaaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("ba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(token).match
        verify(token, times(8)).then(any())
        verify(patternParser, times(10)).parse
    }

    @Test
    fun `Many pattern parser parse false on internal pattern parser parse false`() {
        whenever(patternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                false,
                token,
                pattern
            )
        }

        val patternParserParseResult = Many(patternParser).parse("aaaaaaaaaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("aaaaaaaaaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "bbbbbbbbbacaba"), patternParserParseResult.token.match("bbbbbbbbbacaba"))

        verify(patternParser).parse
    }
}