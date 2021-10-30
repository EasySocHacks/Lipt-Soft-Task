package search

abstract class Searcher(private val names: List<String>) {
    abstract val search: (String) -> List<String>
}