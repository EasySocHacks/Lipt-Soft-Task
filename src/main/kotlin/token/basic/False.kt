package token.basic

import token.Token

object False : Token {
    override val match: (String) -> Token.TokenMatchResult = { input ->
        Token.TokenMatchResult(
            false,
            input
        )
    }
}