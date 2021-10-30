package pattern.basic

import pattern.PatternParser
import token.basic.False

class And(firstPatternParser: PatternParser, secondPatternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val firstParse = firstPatternParser.parse(pattern)

        when (firstParse.parsed) {
            true -> {
                val secondParse = secondPatternParser.parse(firstParse.pattern)

                when (secondParse.parsed) {
                    true -> PatternParserParseResult(
                        true,
                        firstParse.token.then(secondParse.token),
                        secondParse.pattern
                    )

                    false -> PatternParserParseResult(
                        false,
                        False,
                        pattern
                    )
                }
            }

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}