package token

object Character : Token() {
    override val parse: (String) -> Pair<Boolean, String> = { pattern ->
        if (pattern.isNotEmpty() &&
            (alphabet.letters.contains(pattern.first().uppercaseChar()) ||
                    alphabet.letters.contains(pattern.first().lowercaseChar()))
        ) {
            true to pattern.substring(1)
        } else {
            false to pattern
        }
    }
}