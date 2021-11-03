package search

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class IntellijIdeaIshSearcherTest {
    private val intellijIdeaIshSearcher = IntellijIdeaIshSearcher(
        listOf(
            "a.b.FooBarBaz",
            "c.d.FooBar",
            "liptsoft.WishMaker",
            "liptsoft.MindReader",
            "TelephoneOperator",
            "ScubaArgentineOperator",
            "YoureLeavingUsHere",
            "YouveComeToThisPoint",
            "YourEyesAreSpinningInTheirSockets"
        )
    )

    @Test
    fun `IntellijIdeaIshSearcher finds by only uppercase pattern`() {
        assertEquals(
            listOf("ScubaArgentineOperator", "YourEyesAreSpinningInTheirSockets"),
            intellijIdeaIshSearcher.search("A")
        )
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("Z"))
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("ABA"))
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("FB"))
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("FBB"))
    }

    @Test
    fun `IntellijIdeaIshSearcher finds by camelcase pattern`() {
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("FoBa"))
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("FBar"))
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("FooBarBaz"))
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("FooBrBaz"))
        assertEquals(
            listOf("YourEyesAreSpinningInTheirSockets", "YoureLeavingUsHere", "YouveComeToThisPoint"),
            intellijIdeaIshSearcher.search("Yo")
        )
        assertEquals(
            listOf("YourEyesAreSpinningInTheirSockets", "YouveComeToThisPoint"),
            intellijIdeaIshSearcher.search("YoT")
        )
        assertEquals(
            listOf("YourEyesAreSpinningInTheirSockets", "YoureLeavingUsHere"),
            intellijIdeaIshSearcher.search("Your")
        )
    }

    @Test
    fun `IntellijIdeaIshSearcher finds by camelcase pattern with lowercase word in front of it`() {
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("fB"))
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("oB"))
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("ooB"))
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("fooB"))
    }

    @Test
    fun `IntellijIdeaIshSearcher finds by lowercase pattern`() {
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("fb"))
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("fbb"))
        assertEquals(
            listOf(
                "c.d.FooBar",
                "a.b.FooBarBaz",
                "liptsoft.MindReader",
                "ScubaArgentineOperator",
                "TelephoneOperator",
                "liptsoft.WishMaker",
                "YourEyesAreSpinningInTheirSockets",
                "YoureLeavingUsHere"
            ), intellijIdeaIshSearcher.search("a")
        )
    }

    @Test
    fun `IntelliJIdeaIshSearcher finds with whitespace on the end of pattern`() {
        assertEquals(emptyList<String>(), intellijIdeaIshSearcher.search("fb "))
        assertEquals(
            listOf(
                "c.d.FooBar",
                "liptsoft.MindReader",
                "ScubaArgentineOperator",
                "TelephoneOperator",
                "liptsoft.WishMaker"
            ), intellijIdeaIshSearcher.search("r ")
        )
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("baz "))
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("Baz "))
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("Baz\t"))
    }

    @Test
    fun `IntelliJIdeaIshSearcher finds with wildcard in pattern`() {
        assertEquals(listOf("a.b.FooBarBaz"), intellijIdeaIshSearcher.search("f*bb"))
        assertEquals(listOf("TelephoneOperator"), intellijIdeaIshSearcher.search("TelephoneO*r"))
        assertEquals(listOf("c.d.FooBar", "a.b.FooBarBaz"), intellijIdeaIshSearcher.search("o*B"))
        assertEquals(
            listOf(
                "c.d.FooBar",
                "a.b.FooBarBaz",
                "liptsoft.MindReader",
                "ScubaArgentineOperator",
                "TelephoneOperator",
                "liptsoft.WishMaker",
                "YourEyesAreSpinningInTheirSockets",
                "YoureLeavingUsHere",
                "YouveComeToThisPoint"
            ), intellijIdeaIshSearcher.search("o*")
        )
        assertEquals(
            listOf(
                "c.d.FooBar",
                "a.b.FooBarBaz",
                "liptsoft.MindReader",
                "ScubaArgentineOperator",
                "TelephoneOperator",
                "liptsoft.WishMaker",
                "YourEyesAreSpinningInTheirSockets",
                "YoureLeavingUsHere",
                "YouveComeToThisPoint"
            ), intellijIdeaIshSearcher.search("*")
        )
    }
}