package pattern

import pattern.basic.False
import token.basic.EOF

object EndOfClassName : PatternParser() {
    override val parse: (String) -> PatternParserParseResult = { pattern ->
        val parsedPattern = EndOfPatternBlankCharacter.parse(pattern)

        when (parsedPattern.parsed) {
            true -> PatternParserParseResult(
                true,
                EOF,
                parsedPattern.pattern
            )

            false -> False.parse(pattern)
        }
    }
}