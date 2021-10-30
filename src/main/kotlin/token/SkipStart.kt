package token

import token.Token.TokenMatchResult
import token.basic.Skip

class SkipStart(token: Token): Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isEmpty()) {
            true ->TokenMatchResult(
                false,
                input
            )

            false -> {
                val tokenMatch = token.match(input)

                when(tokenMatch.matched) {
                    true -> tokenMatch

                    false -> {
                        val skipMatch = Skip.match(input)

                        when (skipMatch.matched) {
                            true -> SkipStart(token).match(skipMatch.rest)

                            false -> TokenMatchResult(
                                false,
                                skipMatch.rest
                            )
                        }
                    }
                }
            }
        }
    }
}