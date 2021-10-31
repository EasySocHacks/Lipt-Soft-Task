package pattern.basic

import pattern.PatternParser

class OneOrZero(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = patternParser.or(True).parse
}