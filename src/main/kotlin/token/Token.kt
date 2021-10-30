package token

import alphabet.Alphabet

abstract class Token {
    lateinit var alphabet: Alphabet
    abstract val parse: (String) -> Pair<Boolean, String>
}

abstract class TokenUtils(token: Token): Token()

class Many(token: Token): TokenUtils(token) {
    override val parse: (String) -> Pair<Boolean, String> = { pattern ->
        val parsedToken = token.parse(pattern)

        if (parsedToken.first) {
            true to Many(token).parse(parsedToken.second).second
        } else {
            false to pattern
        }
    }
}

class Exact(token: Token, count: Int): TokenUtils(token) {
    override val parse: (String) -> Pair<Boolean, String> = { pattern ->
        if (count < 0) {
            false to pattern
        } else if (count == 0) {
            true to pattern
        } else {
            val parsedToken = token.parse(pattern)

            if (parsedToken.first) {
                val otherExact = Exact(token, count - 1).parse(parsedToken.second)

                if (otherExact.first) {
                    otherExact
                } else {
                    false to pattern
                }
            } else {
                false to pattern
            }
        }
    }
}

class One(token: Token): TokenUtils(token) {
    override val parse: (String) -> Pair<Boolean, String> = Exact(token, 1).parse
}