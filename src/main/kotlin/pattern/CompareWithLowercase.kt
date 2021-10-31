package pattern

import pattern.basic.False
import token.CompareWithLowercase

class CompareWithLowercase(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                CompareWithLowercase(parsedPattern.token),
                parsedPattern.pattern
            )

            false -> False.parse(pattern)
        }
    }
}