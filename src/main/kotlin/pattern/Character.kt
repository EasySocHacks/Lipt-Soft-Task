package pattern

import pattern.basic.False
import token.Character

class Character(character: Char) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern.first() == character) {
            true -> PatternParserParseResult(
                true,
                Character(pattern.first()),
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
                Character(pattern.first()),
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
                Character(pattern.first()),
                pattern.substring(1)
            )

            false -> False.parse(pattern)
        }
    }
}

object BlankCharacter : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isNotEmpty() && pattern[0].isWhitespace()) {
            true -> PatternParserParseResult(
                true,
                Character(pattern.first()),
                pattern.substring(1)
            )

            false -> False.parse(pattern)
        }
    }
}