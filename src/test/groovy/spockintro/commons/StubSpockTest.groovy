package spockintro.commons

import spock.lang.Specification


class StubSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - you can teach stub by double less-than sign
     */
    def "Should return name with correct prefix"() {

        when:
        NameDecorator nameDecorator = Stub()
        nameDecorator.getPrefix() >> "mr"

        Royals royals = new Royals(nameDecorator, "Harold")

        then:
        royals.name == "mr Harold"
    }

    /**
     * KEY POINTS:
     * - values returned by a stub in such cases are more ambitious:
     *      for primitive types, the primitive type’s default value is returned.
     *      for non-primitive numerical values (like BigDecimal), zero is returned.
     */
    def "Should return default value"() {

        when:
        Royals royals = new Royals(Stub(NameDecorator), "Harold")

        then:
        royals.name == "Harold"
    }

    /**
     * KEY POINTS:
     * - for non-numerical values, an “empty” object is returned. This could mean an empty String, an empty collection.
     *
     *  See class org.spockframework.mock.EmptyOrDummyResponse for the details.
     */
    def "Empty objects for collection types"() {

        given:
        DummyListContainer dummyListContainer = Stub()

        when:
        List list = dummyListContainer.getList()

        then:
        list.isEmpty()
    }

    //TODO
//    expect:
//    // Collection & maps
//    assert [1]          //non-empty collection -> true
//    assert ['one': 1]   //non-empty map        -> true
    class DummyListContainer {
        List getList() { }
        BigDecimal getBigDecimal() { }
        DummyListContainer getDummyListContainer() { }
    }
}