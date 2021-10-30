package alphabet.extension

import alphabet.Alphabet

object PunctuationMarkAlphabet: Alphabet {
    override val alias: String = "PUNCTUATION"

    override val letters: List<Char> = listOf(
            '.'
        )
}