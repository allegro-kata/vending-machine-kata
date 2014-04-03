package spockintro.commons

import spock.lang.Specification


class StubSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - you can teach stub by double less-than sign
     */
    def "Teach Stub"() {

        given:
        Money money = Stub()
        money.getValue() >> BigDecimal.TEN

        when:
        BigDecimal value = money.getValue()

        then:
        value == BigDecimal.TEN
    }

    /**
     * KEY POINTS:
     * - values returned by a stub in such cases are more ambitious:
     *      for primitive types, the primitive type’s default value is returned.
     *      for non-primitive numerical values (like BigDecimal), zero is returned.
     */
    def "Default value not only for primitive types"() {

        given:
        Money money = Stub()

        when:
        BigDecimal value = money.getValue()

        then:
        value == BigDecimal.ZERO
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

    class DummyListContainer {
        List getList() { }
    }

    /**
     * KEY POINTS:
     * - for custom values “dummy” object is returned. This could mean an object constructed from its default constructor,
     * or another stub returning default values.
     *
     * See class org.spockframework.mock.EmptyOrDummyResponse for the details
     */
    def "Dummy objects for custom classes"() {

        given:
        Money money = Stub()

        when:
        Money sum = money.add(new Money(2))

        then:
        sum != null
    }

}