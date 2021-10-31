package pattern

import pattern.basic.Many
import pattern.basic.True

object LowercaseWord : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Many(AlphabetLowercaseCharacter).parse
}

object CamelcaseWord : PatternParser() {
    override val parse: (String) -> PatternParserParseResult =
        AlphabetUppercaseCharacter.then(LowercaseWord.or(True)).parse
}