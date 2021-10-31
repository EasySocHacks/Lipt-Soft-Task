package token.basic

import token.Token
import token.Token.TokenMatchResult

object True : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        TokenMatchResult(
            true,
            input
        )
    }
}