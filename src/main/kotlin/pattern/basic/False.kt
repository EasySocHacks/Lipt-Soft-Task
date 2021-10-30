package pattern.basic

import pattern.PatternParser
import token.basic.False

object False : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        PatternParserParseResult(
            false,
            False,
            pattern
        )
    }
}