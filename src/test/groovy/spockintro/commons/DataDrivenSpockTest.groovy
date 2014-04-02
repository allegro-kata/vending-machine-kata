package spockintro.commons

import spock.lang.Specification
import spock.lang.Unroll

import static java.math.MathContext.DECIMAL32

/**
 * @author bartosz walacik
 */
class DataDrivenSpockTest extends Specification {

    /**
     * KEY points:
     * - provide test data using table style or streams style, you pick
     */
    def "should round given value to 2 digits - single test"() {

        when:
        def result = Money.round2(givenValue)

        then:
        result == expectedValue

        where:
        givenValue                       | expectedValue
        0.339d                           | 0.34
        0.33d                            | 0.33
        0.3d                             | 0.3
        new BigDecimal (0.1245,DECIMAL32)| 0.12
        new BigDecimal (0.128,DECIMAL32) | 0.13
        new BigDecimal (0.1,DECIMAL32)   | 0.1
    }

    /**
     * KEY points:
     * - use @Unroll to obtain bunch of tests with extremely readable names
     */
    @Unroll
    def "should round #givenValue.class.simpleName #givenValue to #expectedValue"() {

        when:
        def result = Money.round2(givenValue)

        then:
        result == expectedValue

        where:
        givenValue <<    [0.339d, 0.22d, 0.1d, new BigDecimal (2.333,DECIMAL32)]
        expectedValue << [0.34, 0.22, 0.1, 2.33]
    }
}
