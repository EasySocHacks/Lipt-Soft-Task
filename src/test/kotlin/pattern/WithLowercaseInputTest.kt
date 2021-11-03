package pattern

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import token.Token.TokenMatchResult

internal class WithLowercaseInputTest {

    private val patternParser = mock<PatternParser>()

    @AfterEach
    fun noMoreInteractions() {
        verifyNoMoreInteractions(patternParser)
    }

    @Test
    fun `WithLowercaseInput pattern parser's token match true on inner parser match true if input is lowercase`() {
        whenever(patternParser.parse).thenReturn { pattern ->
            collect(
                Character('a'),
                Character('b'),
                Character('c')
            ).parse(pattern)
        }

        val patternParserParseResult = WithLowercaseInput(patternParser).parse("abc")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "d"), patternParserParseResult.token.match("AbCd"))
        assertEquals(TokenMatchResult(true, "d"), patternParserParseResult.token.match("abcd"))
        assertEquals(TokenMatchResult(false, "ab"), patternParserParseResult.token.match("ab"))

        verify(patternParser).parse
    }
}