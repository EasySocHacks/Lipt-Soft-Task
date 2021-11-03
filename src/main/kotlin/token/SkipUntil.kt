package token

import token.Token.TokenMatchResult
import token.basic.False
import token.basic.Skip

class SkipUntil(token: Token) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isEmpty()) {
            true -> False.match(input)

            false -> {
                val tokenMatch = token.match(input)

                when (tokenMatch.matched) {
                    true -> tokenMatch

                    false -> {
                        val skipMatch = Skip.match(input)

                        when (skipMatch.matched) {
                            true -> {
                                val innerSkipUUntil = SkipUntil(token).match(skipMatch.rest)

                                when (innerSkipUUntil.matched) {
                                    true -> innerSkipUUntil
                                    false -> False.match(input)
                                }
                            }

                            false -> False.match(input)
                        }
                    }
                }
            }
        }
    }
}