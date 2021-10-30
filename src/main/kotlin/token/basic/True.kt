package token.basic

import token.Token

object True : Token {
    override val match: (String) -> Token.TokenMatchResult = { input ->
        Token.TokenMatchResult(
            true,
            input
        )
    }
}