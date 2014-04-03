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
     * - BDD blocks (given, when, then, where) are understood by framework
     * - use Groovy goodness: duck typing, property access and more
     */
    def "should hold given Money value"() {
        given:
        def givenValue = 0.5
        def money = new Money(givenValue)

        when:
        def holdValue = money.value

        then:
        assert holdValue == givenValue
    }
}