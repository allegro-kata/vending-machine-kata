package vendingmachine.domain

import com.google.common.base.Optional
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class MyCassetteTest extends Specification{

    @Unroll
    def "should tube be full after proper coin quantity push"() {
        given:
        def cassette = new MyCassette();

        when:
        times.times {
            cassette.push(Coin.QUARTER)
        }
        def isFull = cassette.isFull(Coin.QUARTER)

        then:
        isFull == result

        where:
        times << [5, 10]
        result << [false, true]
    }

    def "should trow exception when cassette full"() {
        given:
        def cassette = new MyCassette();

        when:
        11.times {
            cassette.push(Coin.QUARTER)
        }

        then:
        thrown FullTubeException
    }

    @Unroll
    def "should tube not be empty after coin push"() {
        given:
        def cassette = new MyCassette();

        when:
        times.times {
            cassette.push(Coin.QUARTER)
        }
        Boolean isEmpty = cassette.isEmpty(Coin.QUARTER);

        then:
        isEmpty == result;

        where:
        times << [0, 5, 8]
        result << [true, false, false]
    }

}
