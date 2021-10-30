package pattern.basic

import pattern.PatternParser
import token.basic.False
import token.basic.True

object EOF: PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        when (pattern.isEmpty()) {
            true -> PatternParserParseResult(
                true,
                True,
                pattern
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}