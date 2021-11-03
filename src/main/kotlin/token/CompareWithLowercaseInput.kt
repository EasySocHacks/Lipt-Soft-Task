package token

import token.Token.TokenMatchResult

class CompareWithLowercaseInput(token: Token) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        token.match(input.lowercase())
    }
}