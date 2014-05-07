package vendingmachine.domain

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class AcceptCoinsTest extends Specification{
    ProductMagazine magazine = Stub()
    CoinCassette cassette = Stub()
    VendingMachine vendingMachine = new VendingMachine(magazine, cassette)

    def "should display 'insert a coin' when ready"() {
        when:
        def greeting = vendingMachine.display

        then:
        greeting == "insert a coin".toUpperCase()
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        when:
        vendingMachine.insert(coin)

        then:
        vendingMachine.display == "CREDIT $expectedValue"

        where:
        coin                             | expectedValue
        Coin.PENNY                       | 0.01
        Coin.NICKEL                      | 0.05
        Coin.DIME                        | 0.10
        Coin.QUARTER                     | 0.25
    }

    @Ignore
    def "should accept series of valid coins and should display the Credit"() {

    }

    @Ignore
    def "should reject invalid coin and shouldn't change the Credit"() {

    }

}
