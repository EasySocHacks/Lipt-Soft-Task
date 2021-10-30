package alphabet.english

import alphabet.Alphabet
import alphabet.extension.PunctuationMarkAlphabet

object ExpandedEnglishAlphabet: Alphabet {
    override val alias: String = "ENG_EXPANDED"
    override val letters: List<Char> =
            EnglishAlphabet.letters.plus(PunctuationMarkAlphabet.letters)
}