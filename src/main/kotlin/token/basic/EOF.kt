package token.basic

import token.Token
import token.Token.TokenMatchResult

object EOF: Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isEmpty()) {
            true -> TokenMatchResult(
                true,
                input
            )

            false -> TokenMatchResult(
                false,
                input
            )
        }
    }
}