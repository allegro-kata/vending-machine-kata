package vendingmachine.domain

import com.google.common.base.Optional
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

    @Ignore
    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        then:
        vendingMachine.getDisplay() == "INSERT A COIN"

    }

    @Ignore
    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
        def coinVal = coin

        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        vendingMachine.insert(coinVal)

        then:

        vendingMachine.getDisplay() == "CREDIT " + coinVal.getValue()

        where:
        coin << [Coin.DIME, Coin.NICKEL, Coin.QUARTER]
    }

    @Ignore
    def "should accept series of valid coins and should display the Credit"() {
        given:
        def coin1 = Coin.QUARTER
        def coin2 = Coin.NICKEL
        def coin3 = Coin.DIME

        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        vendingMachine.insert(coin1)
        vendingMachine.insert(coin2)
        vendingMachine.insert(coin3)

        then:
        vendingMachine.getDisplay() == "CREDIT " + (
        coin1.getValue() +
        coin2.getValue() +
        coin3.getValue() )
    }

    @Ignore
    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        def coinVal = Coin.PENNY

        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        def result = vendingMachine.insert(coinVal)

        then:

        vendingMachine.getDisplay() == "INSERT A COIN"
        result == Optional.of(PENNY)

    }

}
