package pattern

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import token.Token.TokenMatchResult

internal class CharacterTest {

    @Test
    fun `Character token parse true on pattern contains exact token parser's character`() {
        val patternParserParseResult = Character('a').parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("bacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "aabbb"), patternParserParseResult.token.match("aaabbb"))
        assertEquals(TokenMatchResult(false, "baabbb"), patternParserParseResult.token.match("baabbb"))
    }

    @Test
    fun `Character token parse false on pattern dose not contains exact token parser's character`() {
        val patternParserParseResult = Character('a').parse("bacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("bacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "aaabbb"), patternParserParseResult.token.match("aaabbb"))
        assertEquals(TokenMatchResult(false, "baaabbb"), patternParserParseResult.token.match("baaabbb"))
    }

    @Test
    fun `AlphabetUppercaseCharacter token parse true on pattern contains any alphabet character in uppercase`() {
        val patternParserParseResult = AlphabetUppercaseCharacter.parse("Abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("bacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "aaBbb"), patternParserParseResult.token.match("AaaBbb"))
        assertEquals(TokenMatchResult(false, "BaaBbb"), patternParserParseResult.token.match("BaaBbb"))
        assertEquals(TokenMatchResult(false, "aaaBbb"), patternParserParseResult.token.match("aaaBbb"))
        assertEquals(TokenMatchResult(false, "baaBbb"), patternParserParseResult.token.match("baaBbb"))
    }

    @Test
    fun `AlphabetUppercaseCharacter token parse false on pattern does not contains any alphabet character in uppercase`() {
        val patternParserParseResult = AlphabetUppercaseCharacter.parse("abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "AaaBbb"), patternParserParseResult.token.match("AaaBbb"))
        assertEquals(TokenMatchResult(false, "BaaBbb"), patternParserParseResult.token.match("BaaBbb"))
        assertEquals(TokenMatchResult(false, "aaaBbb"), patternParserParseResult.token.match("aaaBbb"))
        assertEquals(TokenMatchResult(false, "baaBbb"), patternParserParseResult.token.match("baaBbb"))
    }

    @Test
    fun `AlphabetLowercaseCharacter token parse true on pattern contains any alphabet character in lowercase`() {
        val patternParserParseResult = AlphabetLowercaseCharacter.parse("abacaba")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("bacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(true, "aaBbb"), patternParserParseResult.token.match("aaaBbb"))
        assertEquals(TokenMatchResult(false, "baaBbb"), patternParserParseResult.token.match("baaBbb"))
        assertEquals(TokenMatchResult(false, "AaaBbb"), patternParserParseResult.token.match("AaaBbb"))
        assertEquals(TokenMatchResult(false, "BaaBbb"), patternParserParseResult.token.match("BaaBbb"))
    }

    @Test
    fun `AlphabetLowercaseCharacter token parse false on pattern does not contains any alphabet character in lowercase`() {
        val patternParserParseResult = AlphabetLowercaseCharacter.parse("Abacaba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("Abacaba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "aaaBbb"), patternParserParseResult.token.match("aaaBbb"))
        assertEquals(TokenMatchResult(false, "baaBbb"), patternParserParseResult.token.match("baaBbb"))
        assertEquals(TokenMatchResult(false, "AaaBbb"), patternParserParseResult.token.match("AaaBbb"))
        assertEquals(TokenMatchResult(false, "BaaBbb"), patternParserParseResult.token.match("BaaBbb"))
    }

    @Test
    fun `BlankCharacter token parse true on pattern contains any whitespace character`() {
        val patternParserParseResult = BlankCharacter.parse(" ")

        assertEquals(true, patternParserParseResult.parsed)
        assertEquals("", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "a"), patternParserParseResult.token.match("a"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
        assertEquals(TokenMatchResult(true, ""), patternParserParseResult.token.match(" "))
    }

    @Test
    fun `BlankCharacter token parse false on pattern does not contains any whitespace character`() {
        val patternParserParseResult = BlankCharacter.parse("aba")

        assertEquals(false, patternParserParseResult.parsed)
        assertEquals("aba", patternParserParseResult.pattern)
        assertEquals(TokenMatchResult(false, "a"), patternParserParseResult.token.match("a"))
        assertEquals(TokenMatchResult(false, ""), patternParserParseResult.token.match(""))
        assertEquals(TokenMatchResult(false, " "), patternParserParseResult.token.match(" "))
    }
}