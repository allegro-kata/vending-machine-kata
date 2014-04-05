package spockintro.commons

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class CustomAssertionSpockTest extends Specification {

    /**
     * KEY POINTS:
     *  - consider custom assertions when testing complex responses
     */
    def "should create Royal Family"() {
        when:
        def queen = RoyalsFactory.createRoyalFamily()

        then:
        queen.name == "Queen Elizabeth"
        queen.children.collect{it.name} as Set == ["Prince Charles", "Diana"] as Set
        def charles = queen.children.find {it.name == "Prince Charles"}
        charles
        charles.children.collect{it.name} as Set == ["Prince William", "Prince Harry"] as Set
    }

    /**
     * KEY POINTS:
     *  - custom assertions should use DSL
     */
    def "should create Royal Family - custom assertion"() {

        when:
        def queen = RoyalsFactory.createRoyalFamily()

        then:
        RoyalsJAssert.assertThat(queen)
                    .hasName("Queen Elizabeth")
                    .hasChildren("Diana","Prince Charles") //change to check pretty assertion error message
                    .andChild("Prince Charles")
                    .hasChildren("Prince William", "Prince Harry")
    }
}
