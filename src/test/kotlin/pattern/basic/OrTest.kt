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

internal class OrTest {
    private val firstToken = mock<Token>()
    private val secondToken = mock<Token>()

    private val firstPatternParser = mock<PatternParser>()
    private val secondPatternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(firstToken, secondToken, firstPatternParser, secondPatternParser)
    }

    @Test
    fun `And pattern parser parse true on first internal parsers parse true`() {
        whenever(firstToken.match).thenReturn { input ->
            Token.TokenMatchResult(
                true,
                "ab"
            )
        }

        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                true,
                firstToken,
                pattern
            )
        }

        val patternParserParseResult = Or(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "ab"), patternParserParseResult.token.match("qqqqqqqqqq"))

        verify(firstToken).match
        verify(firstPatternParser).parse
    }

    @Test
    fun `Or pattern parser parse true on second internal parsers parse true`() {
        whenever(secondToken.match).thenReturn { input ->
            Token.TokenMatchResult(
                true,
                input + "cd"
            )
        }

        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                false,
                firstToken,
                pattern
            )
        }

        whenever(secondPatternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                true,
                secondToken,
                pattern
            )
        }

        val patternParserParseResult = Or(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "qqqqqqqqqqcd"), patternParserParseResult.token.match("qqqqqqqqqq"))

        verify(secondToken).match
        verify(firstPatternParser).parse
        verify(secondPatternParser).parse
    }

    @Test
    fun `Or pattern parser parse false on both internal parsers parse false`() {
        whenever(firstPatternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                false,
                firstToken,
                pattern
            )
        }

        whenever(secondPatternParser.parse).thenReturn { pattern ->
            PatternParser.PatternParserParseResult(
                false,
                secondToken,
                pattern
            )
        }

        val patternParserParseResult = Or(firstPatternParser, secondPatternParser).parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)

        verify(firstPatternParser).parse
        verify(secondPatternParser).parse
    }
}