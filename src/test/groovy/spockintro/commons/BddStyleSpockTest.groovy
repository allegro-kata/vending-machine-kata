package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class BddStyleSpockTest extends Specification {

    //the simplest possible test
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