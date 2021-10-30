package token.basic

import token.Token
import token.Token.TokenMatchResult

object Skip: Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isNotEmpty()) {
            true -> TokenMatchResult(
                true,
                input.substring(1)
            )

            false -> TokenMatchResult(
                false,
                input
            )
        }
    }
}