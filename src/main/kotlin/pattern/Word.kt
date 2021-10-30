package pattern

import pattern.basic.Many
import pattern.basic.True

object Word : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Many(AlphabetCharacter).parse
}

object LowercaseWord : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Many(AlphabetLowercaseCharacter).parse
}

object UppercaseWord : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = Many(AlphabetUppercaseCharacter).parse
}

object CamelcaseWord: PatternParser() {
    override val parse: (String) -> PatternParserParseResult = AlphabetUppercaseCharacter.then(LowercaseWord.or(True)).parse
}