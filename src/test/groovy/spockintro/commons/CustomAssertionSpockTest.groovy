package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class CustomAssertionSpockTest extends Specification {

    /**
     * KEY POINTS:
     *  - consider custom assertions when testing complex objects
     */
    def "should create Royal Family"() {

        when:
        def queen = RoyalsFactory.createRoyalFamily()

        then:
        queen.name == "Queen Elizabeth"
        queen.children.size() == 2
        queen.children.find {it.name == "Diana"}
        def charles = queen.children.find {it.name == "Prince Charles"}
        charles
        charles.children.size() == 2
       // charles.children.find(it.name == "" )

    }
}
