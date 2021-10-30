package pattern

import token.basic.False
import token.basic.Skip

object Wildcard: PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = Character('*').parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                Skip,
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