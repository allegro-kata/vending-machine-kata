package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

import static vendingmachine.domain.Coin.*

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class AcceptCoinsTest extends Specification{

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        then:
        vendingMachine.display == "INSERT A COIN"
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        def result = vendingMachine.insert coin

        then:
        vendingMachine.display == expectedDisplay
        !result.present

        where:
        coin    | expectedDisplay
        NICKEL  | "CREDIT 0.05"
        DIME    | "CREDIT 0.10"
        QUARTER | "CREDIT 0.25"
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        vendingMachine.insert DIME
        vendingMachine.insert NICKEL
        vendingMachine.insert QUARTER

        then:
        vendingMachine.display == "CREDIT 0.40"
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        vendingMachine.insert DIME
        def result = vendingMachine.insert PENNY

        then:
        vendingMachine.display == "CREDIT 0.10"
        result.get() == PENNY
    }

}
