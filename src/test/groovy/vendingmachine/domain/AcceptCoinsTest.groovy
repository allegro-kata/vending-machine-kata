package vendingmachine.domain

import javafx.beans.binding.When
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
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def expected = "INSERT A COIN"
        when:
            def display = machine.getDisplay()
        then:
            display == expected
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def expected = "CREDIT "+coin.value
        when:
            machine.insert(coin)
            def display = machine.getDisplay()
        then:
            display == expected
        where:
            coin << [ Coin.NICKEL, Coin.QUARTER, Coin.DIME ]
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def expected = "CREDIT 0.40"
        when:
            machine.insert(Coin.DIME)
            machine.insert(Coin.QUARTER)
            machine.insert(Coin.NICKEL)
            def display = machine.getDisplay()
        then:
            display == expected
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def expected = "CREDIT 0.10"
        when:
            machine.insert(Coin.DIME)
            machine.insert(Coin.PENNY)
            def display = machine.getDisplay()
        then:
            display == expected
    }

}
