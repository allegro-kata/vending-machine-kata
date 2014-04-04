package spockintro.commons

/**
 * @author bartosz walacik
 */
class RoyalsAssert {
    Royals actual

    static RoyalsJAssert assertThat(Royals actual) {
        new RoyalsJAssert(actual: actual)
    }

    RoyalsJAssert hasName(String expected) {
        assert actual.name == expected
        this
    }

    RoyalsJAssert hasChildren(String... expectedNames){
       assert actual.children.collect{it.name} as Set == expectedNames as Set
       this
    }

    RoyalsJAssert andChild(String expectedName){
        def found = actual.children.find{it.name == expectedName}
        assert found
        new RoyalsJAssert(actual:found)
    }
}
