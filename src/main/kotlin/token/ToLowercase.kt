package token

import token.Token.TokenMatchResult

class ToLowercase(token: Token) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        token.match(input.lowercase())
    }
}