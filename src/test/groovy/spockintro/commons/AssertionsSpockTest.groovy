package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class AssertionsSpockTest extends Specification {
    /**
     * KEY points:
     *  - no boilerplate code, assertions are just boolean expressions
     *  - in 'then' block you can omit assert keyword
     *  - assertions are smart, it's not plain equals(),
     *    SPOCK not bothers you with stupid false positive
     *  - error messages are clear and readable
     *  - only the last line of stack trace is shown
     */
    def "should add two Money values"() {
        given:
        def money1 = new Money(0.5)
        def money2 = new Money(0.5)

        when:
        Money result = money1.add(money2)

        then:
        result.value == 1.01
    }
}
