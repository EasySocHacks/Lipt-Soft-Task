package pattern

import token.SkipStart
import token.basic.False

class SkipStart(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val paredPattern = patternParser.parse(pattern)

        when (paredPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                SkipStart(paredPattern.token),
                paredPattern.pattern
            )

            false -> PatternParserParseResult(
                false,
                False,
                paredPattern.pattern
            )
        }
    }
}