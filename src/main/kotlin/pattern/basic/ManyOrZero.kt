package pattern.basic

import pattern.PatternParser

class ManyOrZero(patternParser: PatternParser) : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Many(patternParser).or(True).parse
}