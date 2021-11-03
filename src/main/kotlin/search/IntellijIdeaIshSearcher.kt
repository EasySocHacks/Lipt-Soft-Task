package search

import pattern.*
import pattern.basic.EndOfPattern
import pattern.basic.Many
import pattern.basic.ManyOrZero
import pattern.basic.OneOrZero

class IntellijIdeaIshSearcher(names: List<String>) : Searcher(names) {
    override val search: (String) -> List<String> = { pattern ->
        val wildcardPatterParser =
            ManyOrZero(
                collect(
                    OneOrZero(LowercaseWord),
                    WildcardUntil(
                        OneOrZero(LowercaseWord)
                    )
                )
            )

        val camelcasePatternParser = collect(
            either(
                Many(
                    collect(
                        OneOrZero(LowercaseWord),
                        WildcardUntil(
                            OneOrZero(LowercaseWord)
                        )
                    )
                ),
                OneOrZero(LowercaseWord)
            ),
            collect(
                collect(
                    CamelcaseWord,
                    wildcardPatterParser
                ),
                ManyOrZero(
                    SkipUntil(
                        collect(
                            CamelcaseWord,
                            wildcardPatterParser
                        )
                    )
                ),
                ManyOrZero(EndOfClassName)
            )
        )

        val lowercasePatternParser = collect(
            Many(
                SkipUntil(
                    WithLowercaseInput(
                        either(
                            AlphabetLowercaseCharacter,
                            WildcardUntil(
                                OneOrZero(AlphabetLowercaseCharacter)
                            )
                        )
                    )
                )
            ),
            ManyOrZero(EndOfClassName)
        )

        val patternParser =
            SkipUntil(
                either(
                    camelcasePatternParser,
                    lowercasePatternParser
                )
            ).then(EndOfPattern)

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