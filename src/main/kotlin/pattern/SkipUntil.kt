package pattern

import pattern.basic.False
import token.SkipUntil

class SkipUntil(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                SkipUntil(parsedPattern.token),
                parsedPattern.pattern
            )

            false -> False.parse(parsedPattern.pattern)
        }
    }
}