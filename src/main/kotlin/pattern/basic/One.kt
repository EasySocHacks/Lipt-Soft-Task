package pattern.basic

import pattern.PatternParser

class One(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Exact(patternParser, 1).parse
}