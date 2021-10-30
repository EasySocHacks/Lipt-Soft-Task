package pattern

import token.ToLowercase
import token.basic.False

class ToLowercase(patternParser: PatternParser): PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                ToLowercase(parsedPattern.token),
                parsedPattern.pattern
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}