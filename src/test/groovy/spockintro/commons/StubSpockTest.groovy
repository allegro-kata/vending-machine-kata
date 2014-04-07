package spockintro.commons

import spock.lang.Specification


class StubSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - you can teach stub by double less-than sign
     */
    def "Should return name with correct prefix"() {

        when:
        NameDecorator nameDecorator = Stub() {
            getPrefix() >> "mr "
        }

        Royals royals = new RoyalsDecorated(nameDecorator, "Harold")

        then:
        royals.name == "mr Harold"
    }

    /**
     * KEY POINTS:
     * - values returned by a stub in such cases are more ambitious:
     *   for primitive types, the primitive type’s default value is returned.
     *   for non-primitive numerical values (like BigDecimal), zero is returned.
     */
    def "Should return default value"() {

        when:
        Royals royals = new RoyalsDecorated(Stub(NameDecorator), "Harold")

        then:
        royals.name == "Harold"
    }

    /**
     * KEY POINTS:
     * - for non-numerical values, an “empty” object is returned. This could mean an empty String, an empty collection.
     */
    def "should return default value for collection"() {

        given:
        DummyListContainer dummyListContainer = Stub()

        when:
        List list = dummyListContainer.list

        then:
        list.isEmpty()
    }

    /**
     * KEY POINTS:
     * - stub stubbing also nested properties
     */
    def "should return default value for nested object"() {

        given:
        DummyListContainer dummyListContainer = Stub()

        when:
        List list = dummyListContainer.dummyListContainer.dummyListContainer.list

        then:
        list.isEmpty()
    }


    class DummyListContainer {
        List getList() { }
        DummyListContainer getDummyListContainer() { }
    }
}