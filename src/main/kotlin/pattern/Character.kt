package pattern

import pattern.basic.False
import pattern.basic.True
import token.Token
import token.Token.TokenMatchResult

class Character(character: Char) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern.first() == character) {
            true -> PatternParserParseResult(
                true,
                CharacterToken(pattern.first()),
                pattern.substring(1)
            )

            false -> False.parse(pattern)
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

            false -> False.parse(pattern)
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

            false -> False.parse(pattern)
        }
    }
}

object BlankCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern[0].isWhitespace()) {
            true -> True.parse(pattern.substring(1))

            false -> False.parse(pattern)
        }
    }
}

class CharacterToken(character: Char) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isNotEmpty() && input.first() == character) {
            true -> token.basic.True.match(input.substring(1))

            false -> token.basic.False.match(input)
        }
    }
}