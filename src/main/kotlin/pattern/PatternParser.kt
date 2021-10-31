package pattern

import alphabet.Alphabet
import alphabet.english.EnglishAlphabet
import pattern.basic.And
import pattern.basic.False
import pattern.basic.Or
import pattern.basic.True
import token.Token

abstract class PatternParser {
    data class PatternParserParseResult(
        val parsed: Boolean,
        val token: Token,
        val pattern: String
    )

    val alphabet: Alphabet = EnglishAlphabet
    abstract val parse: (String) -> PatternParserParseResult

    fun then(patternParser: PatternParser): PatternParser = And(this, patternParser)

    fun and(patternParser: PatternParser): PatternParser = then(patternParser)

    fun or(patternParser: PatternParser): PatternParser = Or(this, patternParser)
}

fun collect(vararg patternParsers: PatternParser): PatternParser {
    var parser: PatternParser = True

    for (patternParser in patternParsers) {
        parser = parser.then(patternParser)
    }

    return parser
}

fun either(vararg patternParsers: PatternParser): PatternParser {
    var parser: PatternParser = False

    for (patternParser in patternParsers) {
        parser = parser.or(patternParser)
    }

    return parser
}