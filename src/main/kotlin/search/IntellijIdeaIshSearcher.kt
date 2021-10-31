package search

import pattern.*
import pattern.basic.EOF
import pattern.basic.Many
import pattern.basic.ManyOrZero
import pattern.basic.OneOrZero

class IntellijIdeaIshSearcher(names: List<String>) : Searcher(names) {
    override val search: (String) -> List<String> = { pattern ->
        val wildcardPatterParse =
            ManyOrZero(
                collect(
                    OneOrZero(LowercaseWord),
                    Wildcard,
                    OneOrZero(LowercaseWord)
                )
            )

        val patternParser =
            collect(
                ManyOrZero(Package),
                SkipSUntil(
                    either(
                        collect(
                            either(
                                Many(
                                    collect(
                                        OneOrZero(LowercaseWord),
                                        Wildcard,
                                        OneOrZero(LowercaseWord)
                                    )
                                ),
                                OneOrZero(LowercaseWord)
                            ),
                            collect(
                                collect(
                                    CamelcaseWord,
                                    wildcardPatterParse
                                ),
                                ManyOrZero(
                                    SkipSUntil(
                                        collect(
                                            CamelcaseWord,
                                            wildcardPatterParse
                                        )
                                    )
                                ),
                                ManyOrZero(EndOfClassName)
                            )
                        ),
                        collect(
                            Many(
                                SkipSUntil(
                                    ToLowercase(
                                        either(
                                            AlphabetLowercaseCharacter,
                                            Wildcard
                                        )
                                    )
                                )
                            ),
                            ManyOrZero(EndOfClassName)
                        )
                    )
                )
            ).then(EOF)

        val parsedPattern = patternParser.parse(pattern)

        when (parsedPattern.parsed) {
            true -> names.filter { name ->
                parsedPattern.token.match(name).matched
            }.sortedBy { name ->
                name.substring(name.lastIndexOf('.') + 1)
            }
            false -> emptyList()
        }
    }
}