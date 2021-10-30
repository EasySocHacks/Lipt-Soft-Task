package pattern.basic

import pattern.PatternParser

class Or(firstPatternParser: PatternParser, secondPatternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val firstParse = firstPatternParser.parse(pattern)

        if (firstParse.parsed) {
            firstParse
        } else {
            secondPatternParser.parse(pattern)
        }
    }
}