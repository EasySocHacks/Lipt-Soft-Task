package pattern

object Package : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = LowercaseWord.then(Character('.')).parse
}