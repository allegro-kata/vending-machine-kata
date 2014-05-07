package vendingmachine.domain

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll
import spockintro.commons.Money

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class AcceptCoinsTest extends Specification{

//    @Ignore
    def "should display 'insert a coin' when ready"() {
        given:
        ProductMagazine magazine = Mock()
        CoinCassette cassette = Mock()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:
        def display = machine.display

        then:
        display == "INSERT A COIN"
    }

//    @Ignore
    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
        ProductMagazine magazine = Mock()
        CoinCassette cassette = Mock()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:
        machine.insert(coin)

        then:
        machine.display == expectedValue

        where:
        coin                       | expectedValue
        Coin.DIME                  | "CREDIT 0.10"
        Coin.QUARTER               | "CREDIT 0.25"
        Coin.NICKEL                | "CREDIT 0.05"
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
        ProductMagazine magazine = Mock()
        CoinCassette cassette = Mock()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:
        machine.insert(Coin.DIME)
        machine.insert(Coin.QUARTER)
        machine.insert(Coin.DIME)
        machine.insert(Coin.NICKEL)

        then:
        machine.display == "CREDIT 0.50"
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        ProductMagazine magazine = Mock()
        CoinCassette cassette = Mock()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:

        machine.insert(Coin.NICKEL)
        def invalidCoin = machine.insert(Coin.PENNY)

        then:
        machine.display == "CREDIT 0.05"
        invalidCoin.get() == Coin.PENNY
    }

}
