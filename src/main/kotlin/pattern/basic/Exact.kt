package pattern.basic

import pattern.PatternParser
import token.basic.False
import token.basic.True

class Exact(patternParser: PatternParser, count: Int) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when {
            count < 0 -> PatternParserParseResult(
                false,
                False,
                pattern
            )

            count == 0 -> PatternParserParseResult(
                true,
                True,
                pattern
            )

            else -> {
                val parsedPattern = patternParser.parse(pattern)

                when (parsedPattern.parsed) {
                    true -> {
                        val otherExact = Exact(patternParser, count - 1).parse(parsedPattern.pattern)

                        when (otherExact.parsed) {
                            true -> PatternParserParseResult(
                                true,
                                parsedPattern.token.then(otherExact.token),
                                otherExact.pattern
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
    }
}