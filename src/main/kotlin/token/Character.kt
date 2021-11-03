package token

import token.Token.TokenMatchResult

class Character(character: Char) : Token {
    override val match: (String) -> TokenMatchResult = { input ->
        when (input.isNotEmpty() && input.first() == character) {
            true -> token.basic.True.match(input.substring(1))

            false -> token.basic.False.match(input)
        }
    }
}