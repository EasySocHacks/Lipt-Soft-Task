package token.basic

import token.Token
import token.Token.TokenMatchResult

class Or(firstToken: Token, secondToken: Token) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        val firstMatch = firstToken.match(input)

        when (firstMatch.matched) {
            true -> firstMatch
            false -> secondToken.match(input)
        }
    }
}