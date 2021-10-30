package token

import token.basic.And
import token.basic.Or

interface Token {
    data class TokenMatchResult(
        val matched: Boolean,
        val rest: String
    )

    val match: (String) -> TokenMatchResult

    fun then(token: Token): Token = And(this, token)

    fun and(token: Token): Token = then(token)

    fun or(token: Token): Token = Or(this, token)
}