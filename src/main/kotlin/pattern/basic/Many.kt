package pattern.basic

import pattern.PatternParser

class Many(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> {
                val manyParsed = Many(patternParser).parse(parsedPattern.pattern)

                when (manyParsed.parsed) {
                    true -> PatternParserParseResult(
                        true,
                        parsedPattern.token.then(manyParsed.token),
                        manyParsed.pattern
                    )

                    false -> PatternParserParseResult(
                        true,
                        parsedPattern.token,
                        parsedPattern.pattern
                    )
                }
            }

            false -> False.parse(pattern)
        }
    }
}