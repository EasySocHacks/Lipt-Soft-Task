package token.basic

import token.Token
import token.Token.TokenMatchResult

object False : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        TokenMatchResult(
            false,
            input
        )
    }
}