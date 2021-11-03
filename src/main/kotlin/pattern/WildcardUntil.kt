package pattern

import pattern.basic.False

class WildcardUntil(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = Character('*').parse(pattern)

        when (parsedPattern.parsed) {
            true -> SkipUntil(patternParser).parse(parsedPattern.pattern)

            false -> False.parse(pattern)
        }
    }
}