package spockintro.commons

/**
 * @author bartosz walacik
 */
class RoyalsAssert {
    Royals actual

    static RoyalsAssert assertThat(Royals actual) {
        new RoyalsAssert(actual: actual)
    }

    RoyalsAssert hasName(String expected) {
        assert actual.name == expected
        this
    }

    RoyalsAssert hasChildren(String... expectedNames){
       assert actual.children.collect{it.name} as Set == expectedNames as Set
       this
    }

    RoyalsAssert andChild(String expectedName){
        def found = actual.children.find{it.name == expectedName}
        assert found
        new RoyalsAssert(actual:found)
    }
}
