package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class AssertionsTest extends Specification {
    def "should add two Money values"() {
        given:
        def money1 = new Money(0.5)
        def money2 = new Money(0.5)

        when:
        Money result = money1.add(money2)

        then:
        result.value == 1
    }
}
