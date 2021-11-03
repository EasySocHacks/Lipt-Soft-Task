package pattern

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import token.Token

internal class SkipUntilTest {
    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(patternParser)
    }

    @Test
    fun `SkipUntil pattern parser return token that skips until inner parser's token in true`() {
        whenever(patternParser.parse).thenReturn { Character('a').parse(it) }

        val patternParserParseResult = SkipUntil(patternParser).parse("aba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("ba", patternParserParseResult.pattern)
        assertEquals(Token.TokenMatchResult(true, "zzzfff"), patternParserParseResult.token.match("pppppppoooooooooooqqqqqqazzzfff"))
        assertEquals(Token.TokenMatchResult(false, "pppppppoooooooooooqqqqqqzzzfff"), patternParserParseResult.token.match("pppppppoooooooooooqqqqqqzzzfff"))
        assertEquals(Token.TokenMatchResult(false, ""), patternParserParseResult.token.match(""))

        verify(patternParser).parse
    }
}