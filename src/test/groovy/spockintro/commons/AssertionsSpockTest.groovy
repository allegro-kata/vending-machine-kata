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
        result.value == 1
    }

    /**
     * KEY points:
     * - Groovy Truth
     * - http://docs.codehaus.org/display/GROOVY/Groovy+Truth
     */
    def "should evaluate assertions with Groovy Truth"() {

        expect:
        // Collection & maps
        assert [1]          //non-empty collection -> true
        assert ['one': 1]   //non-empty map        -> true

        assert ![]          //empty collection     -> false
        assert ![:]         //empty map           -> false

        // Number
        assert 1            //non-zero     -> true
        assert !0           //zero         -> false

        // Strings
        assert 'a'          //non-empty    -> true
        assert ' '          //non-empty    -> true
        assert !''          //empty        -> false

        // Object references
        assert this         //not null     -> true
        assert !null        //null         -> false
    }
}