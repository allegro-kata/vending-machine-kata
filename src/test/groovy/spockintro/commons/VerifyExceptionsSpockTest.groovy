package spockintro.commons

import spock.lang.Specification


class VerifyExceptionsSpockTest extends Specification {

    /**
     * KEY POINTS:
     * - Specification provides method thrown() and thrown(class)
     */
    def "should throw IllegalArgumentException when try to round null"() {

        when:
        Money.round2(null)

        then:
        thrown(IllegalArgumentException)
        //      another syntax: IllegalArgumentException exception = thrown()
    }

    /**
     * KEY POINTS:
     * - you can explore the exception, thrown method returns it
     * - you can omit parenthesis, then test looks great!
     */
    def "should throw IllegalArgumentException when try to round null 2"() {

        when:
        Money.round2(null)

        then:
        def exception = thrown IllegalArgumentException
        exception.message == "Value cannot be null"
    }

    /**
     * KEY POINTS:
     * - you can check that exception wasn't thrown
     */
    def "should not throw IllegalArgumentException when try to round correct value"() {

        when:
        Money.round2(3)

        then:
        notThrown IllegalArgumentException
    }
}