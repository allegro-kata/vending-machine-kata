package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll
import vendingmachine.domain.exceptions.FullTubeException

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

    def "should say SORRY and reject valid coin if internal Coin Cassette is full"() {
        given:
        CoinCassette cassette = Stub(CoinCassette)
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), cassette)

        cassette.isFull(DIME) >> true
        cassette.push(DIME) >> { throw new FullTubeException(DIME) }

        when:
        vendingMachine.insert(NICKEL)
        def result = vendingMachine.insert(DIME)

        then:
        result.get() == DIME
        vendingMachine.display == "CASSETTE IS FULL, SORRY"
        vendingMachine.display == "CREDIT 0.05"
    }
}
