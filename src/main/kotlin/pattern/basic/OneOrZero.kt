package pattern.basic

import pattern.PatternParser

class OneOrZero(patternParser: PatternParser): PatternParser() {
    override val parse: (String) -> PatternParserParseResult = One(patternParser).or(True).parse
}