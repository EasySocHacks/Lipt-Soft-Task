package pattern

import token.Token
import token.Token.TokenMatchResult
import token.basic.False
import token.basic.True

class Character(character: Char) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern.first() == character) {
            true -> PatternParserParseResult(
                true,
                CharacterToken(pattern.first()),
                pattern.substring(1)
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}

object AlphabetCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() &&
                (alphabet.letters.contains(pattern.first().uppercaseChar()) ||
                        alphabet.letters.contains(pattern.first().lowercaseChar()))
        ) {
            true -> PatternParserParseResult(
                true,
                CharacterToken(pattern.first()),
                pattern.substring(1)
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}

object AlphabetUppercaseCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() &&
                (alphabet.letters.map { it.uppercaseChar() }.contains(pattern.first()))
        ) {
            true -> PatternParserParseResult(
                true,
                CharacterToken(pattern.first()),
                pattern.substring(1)
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}

object AlphabetLowercaseCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() &&
                (alphabet.letters.map { it.lowercaseChar() }.contains(pattern.first()))
        ) {
            true -> PatternParserParseResult(
                true,
                CharacterToken(pattern.first()),
                pattern.substring(1)
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}

object BlankCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern[0].isWhitespace()) {
            true -> PatternParserParseResult(
                true,
                True,
                pattern.substring(1)
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}

class CharacterToken(character: Char) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isNotEmpty() && input.first() == character) {
            true -> TokenMatchResult(
                true,
                input.substring(1)
            )

            false -> TokenMatchResult(
                false,
                input
            )
        }
    }
}