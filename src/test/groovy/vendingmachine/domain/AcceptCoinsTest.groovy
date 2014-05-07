package vendingmachine.domain

import com.google.common.base.Optional
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

    VendingMachine vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

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
        vendingMachine.display == expectedValue

        where:
        coin                             | expectedValue
        Coin.NICKEL                      | "CREDIT $Coin.NICKEL.value"
        Coin.DIME                        | "CREDIT $Coin.DIME.value"
        Coin.PENNY                       | "INSERT A COIN"
        Coin.QUARTER                     | "CREDIT $Coin.QUARTER.value"
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
        def expected = "CREDIT 0.75"

        when:
        vendingMachine.insert(Coin.QUARTER)
        vendingMachine.insert(Coin.QUARTER)
        vendingMachine.insert(Coin.QUARTER)

        then:
        vendingMachine.display == expected

    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        def expected = "CREDIT $Coin.QUARTER.value"

        when:
        vendingMachine.insert(Coin.QUARTER)
        def invalidCoins = vendingMachine.insert(Coin.PENNY)

        then:
        invalidCoins == Optional.of(Coin.PENNY);
        vendingMachine.display == expected
    }

}
