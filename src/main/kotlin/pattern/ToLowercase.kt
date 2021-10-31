package pattern

import pattern.basic.False
import token.ToLowercase

class ToLowercase(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                ToLowercase(parsedPattern.token),
                parsedPattern.pattern
            )

            false -> False.parse(pattern)
        }
    }
}