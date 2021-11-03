package pattern.basic

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import pattern.PatternParser
import pattern.PatternParser.PatternParserParseResult
import token.Token
import token.Token.TokenMatchResult

internal class AndTest {
    private val firstToken = mock<Token>()
    private val secondToken = mock<Token>()

    private val firstPatternParser = mock<PatternParser>()
    private val secondPatternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(firstToken, secondToken, firstPatternParser, secondPatternParser)
    }

    @Test
    fun `And pattern parser parse true on both internal parsers parse true`() {
        whenever(firstToken.match).thenReturn { input ->
            TokenMatchResult(
                true,
                "ab"
            )
        }

        whenever(firstToken.then(any())).thenReturn(token.basic.And(firstToken, secondToken))

        whenever(secondToken.match).thenReturn { input ->
            TokenMatchResult(
                true,
                input + "cd"
            )
        }

        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                true,
                firstToken,
                pattern
            )
        }

        whenever(secondPatternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                true,
                secondToken,
                pattern
            )
        }

        val patternParserParseResult = And(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "abcd"), patternParserParseResult.token.match("qqqqqqqqqq"))

        verify(firstToken).match
        verify(firstToken).then(secondToken)
        verify(secondToken).match
        verify(firstPatternParser).parse
        verify(secondPatternParser).parse
    }

    @Test
    fun `And pattern parser parse false on first internal parsers parse false`() {
        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                false,
                firstToken,
                pattern
            )
        }

        val patternParserParseResult = And(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "qqqqqqqqqq"), patternParserParseResult.token.match("qqqqqqqqqq"))

        verify(firstPatternParser).parse
    }

    @Test
    fun `And pattern parser parse false on second internal parsers parse false`() {
        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                true,
                firstToken,
                pattern
            )
        }

        whenever(secondPatternParser.parse).thenReturn { pattern ->
            PatternParserParseResult(
                false,
                secondToken,
                pattern
            )
        }

        val patternParserParseResult = And(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "qqqqqqqqqq"), patternParserParseResult.token.match("qqqqqqqqqq"))

        verify(firstPatternParser).parse
        verify(secondPatternParser).parse
    }
}