package token.basic

import token.Token
import token.Token.TokenMatchResult

class And(firstToken: Token, secondToken: Token): Token {
    override val match: (String) -> TokenMatchResult = { input ->
        val firstMatch = firstToken.match(input)

        when (firstMatch.matched) {
            true -> secondToken.match(firstMatch.rest)
            false -> TokenMatchResult(
                false,
                input
            )
        }
    }
}