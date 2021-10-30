import alphabet.english.EnglishAlphabet
import token.Character
import token.One

fun main(args: Array<String>) {
    val pattern = "hello"

    Character.alphabet = EnglishAlphabet
    println(One(Character).parse(pattern))
}