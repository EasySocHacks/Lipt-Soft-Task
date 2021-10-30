package pattern

import token.basic.EOF
import token.basic.False

object EndOfClassName: PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = BlankCharacter.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                EOF,
                parsedPattern.pattern
            )

            false -> PatternParserParseResult(
                false,
                False,
                pattern
            )
        }
    }
}