package spockintro.commons

import spock.lang.Specification


class VerifyExceptionsSpockTest extends Specification {

    /**
     * KEY points:
     * - Specification provides method thrown() and thrown(class)
     * - you can omit parenthesis, then test looks great!
     */
    def "should throw IllegalArgumentException when try to round null"() {

        when:
        Money.round2(null)

        then:
        thrown IllegalArgumentException
    }

    /**
     * KEY points:
     * - you can explore the exception, thrown method returns it
     */
    def "should throw IllegalArgumentException when try to round null 2"() {

        when:
        Money.round2(null)

        then:
        def exception = thrown IllegalArgumentException
//      another syntax: IllegalArgumentException exception = thrown()
        exception.message == "Value cannot be null"
    }

    /**
     * KEY points:
     * - you can check that exception wasn't thrown
     */
    def "should not throw IllegalArgumentException when try to round correct value"() {

        when:
        Money.round2(3)

        then:
        notThrown IllegalArgumentException
    }
}