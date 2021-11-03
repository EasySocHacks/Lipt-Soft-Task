### This is an implementation of Lipt-Soft task of Intellij Idea ish class searcher (ctr + N).
Fully task description you can find [here](TASK_DESCRIPTION.md)

---
# The Idea
### Patter parser
Input pattern is parsing by set of parser combinators located [here](src/main/kotlin/pattern). 
Each pattern parser return contains a value of parsed pattern in the following format:
```kotlin
data class PatternParserParseResult(
    val parsed: Boolean,
    val token: Token,
    val pattern: String
)
```
where:
* **parsed** - boolean valued by is either received pattern was parsed by current [PatternParser](src/main/kotlin/pattern/PatternParser.kt)
* **token** - a [Token](src/main/kotlin/token/Token.kt) that match input string according to parsed pattern by [PatternParser](src/main/kotlin/pattern/PatternParser.kt)
* **pattern** - rest of the pattern not parsed by current [PatternParser](src/main/kotlin/pattern/PatternParser.kt)

### Token
Input string is matching by combined tokens located [here](src/main/kotlin/token). 
Each token matcher return contains a value of matched input in the following format:
```kotlin
data class TokenMatchResult(
        val matched: Boolean,
        val rest: String
    )
```
where:
* **matched** - boolean valued by is either received string is matching the [Token](src/main/kotlin/token/Token.kt)
* **rest** - rest of the input not matched by this current [Token](src/main/kotlin/token/Token.kt)

# Searcher
Searcher consist of [PatternParsers](src/main/kotlin/pattern/PatternParser.kt) combined.
You can find searcher implementations [here](src/main/kotlin/search).

## Hint
In order to prevent boilerplate code while creating your own searchers, use *And* and *Or* pattern parser's and token's aliases
(**collect** and **either**).
This should be look like this:
```kotlin
collect(
    Character('a'),
    Character('b')
)
// Matches exact 'ab' input prefix

either(
    Character('a'),
    Character('b')
)
// Matches 'a' input prefix or 'b' in case of first condition if false 
```