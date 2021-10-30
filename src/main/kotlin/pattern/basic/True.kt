package pattern.basic

import pattern.PatternParser
import token.basic.True

object True : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        PatternParserParseResult(
            true,
            True,
            pattern
        )
    }
}