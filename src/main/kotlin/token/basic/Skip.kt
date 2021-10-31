package token.basic

import token.Token
import token.Token.TokenMatchResult

object Skip : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isNotEmpty()) {
            true -> True.match(input.substring(1))

            false -> False.match(input)
        }
    }
}