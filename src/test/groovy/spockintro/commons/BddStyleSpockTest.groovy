package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class BddStyleSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - use spaces and symbols in method names, test name should
     *   be human readable & descriptive
     * - BDD blocks are required by framework
     * - each comparison in this block is treated as assertion
     */
    def "should hold given Money value"() {
        given:
        def givenValue = 0.5
        def money = new Money(givenValue)

        when:
        def holdValue = money.value

        then:
        holdValue == givenValue
    }
}