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

    def "should display 'insert a coin' when ready"() {
        given:
        ProductMagazine magazine = Stub()
        CoinCassette cassette = Stub()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:
        def display = machine.getDisplay()

        then:
        display == "INSERT A COIN"

    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
        //Coin coin = new Coin()
        ProductMagazine magazine = Stub()
        CoinCassette cassette = Stub()
        VendingMachine machine = new VendingMachine(magazine, cassette)

        when:
        machine.insert(Coin.NICKEL)
        def display = machine.getDisplay()

        then:
        display == "CREDIT 0.05"

    }

    @Ignore
    def "should accept series of valid coins and should display the Credit"() {

    }

    @Ignore
    def "should reject invalid coin and shouldn't change the Credit"() {

    }

}
