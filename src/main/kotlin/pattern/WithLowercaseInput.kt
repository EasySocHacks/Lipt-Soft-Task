package pattern

import pattern.basic.False
import token.CompareWithLowercaseInput

class WithLowercaseInput(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                CompareWithLowercaseInput(parsedPattern.token),
                parsedPattern.pattern
            )

            false -> False.parse(pattern)
        }
    }
}