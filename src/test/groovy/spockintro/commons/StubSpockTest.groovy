package spockintro.commons

import spock.lang.Specification


class StubSpockTest extends Specification {

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
     * However, the values returned by a stub in such cases are more ambitious:
     * For primitive types, the primitive type’s default value is returned.
     * For non-primitive numerical values (like BigDecimal), zero is returned.
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
     * For non-numerical values, an “empty” or “dummy” object is returned. This could mean an empty String, an empty collection,
     * an object constructed from its default constructor, or another stub returning default values.
     * See class org.spockframework.mock.EmptyOrDummyResponse for the details.
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
     * For non-numerical values, an “empty” or “dummy” object is returned. This could mean an empty String, an empty collection,
     * an object constructed from its default constructor, or another stub returning default values.
     *
     * remember GOTO org.spockframework.mock.EmptyOrDummyResponse for the details
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