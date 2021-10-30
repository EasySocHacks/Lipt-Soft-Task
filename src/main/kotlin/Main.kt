import search.IntellijIdeaIshSearcher

fun main(args: Array<String>) {
    val intellijIdeaIshSearcher = IntellijIdeaIshSearcher(listOf(
        "a.b.FooBarBaz",
        "c.d.FooBar",
        "liptsoft.WishMaker",
        "liptsoft.MindReader",
        "TelephoneOperator",
        "ScubaArgentineOperator",
        "YoureLeavingUsHere",
        "YouveComeToThisPoint",
        "YourEyesAreSpinningInTheirSockets"
    ))

    println(intellijIdeaIshSearcher.search("a"))
}