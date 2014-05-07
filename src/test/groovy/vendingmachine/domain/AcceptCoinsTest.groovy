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

    def "should display 'insert a coin' when ready"() {
        given:
        ProductMagazine magazine = Stub();
        CoinCassette cassette = Stub();
        def vendingMachine = new VendingMachine(magazine, cassette);

        when:
        def displayResult = vendingMachine.display

        then:
        displayResult == 'INSERT A COIN'
    }

    @Unroll
    def "should accept one valid #givenValue and display its value as Credit"() {
        given:
        ProductMagazine magazine = Stub();
        CoinCassette cassette = Stub();
        def vendingMachine = new VendingMachine(magazine, cassette);

        when:
        vendingMachine.insert(givenValue)
        def displayResult = vendingMachine.display

        then:
        displayResult == expectedValue

        where:
        givenValue <<    [Coin.NICKEL, Coin.DIME, Coin.QUARTER]
        expectedValue << ['CREDIT 0.05', 'CREDIT 0.10', 'CREDIT 0.25']
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
        ProductMagazine magazine = Stub();
        CoinCassette cassette = Stub();
        def vendingMachine = new VendingMachine(magazine, cassette);

        when:
        vendingMachine.insert(Coin.NICKEL);
        vendingMachine.insert(Coin.DIME);
        vendingMachine.insert(Coin.QUARTER);
        def displayResult = vendingMachine.display

        then:
        displayResult == 'CREDIT 0.40'
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        ProductMagazine magazine = Stub();
        CoinCassette cassette = Stub();
        def vendingMachine = new VendingMachine(magazine, cassette);

        when:
        vendingMachine.insert(Coin.NICKEL);
        def invalidCoins = vendingMachine.insert(Coin.PENNY);
        def displayResult = vendingMachine.display

        then:
        displayResult == 'CREDIT 0.05'
        invalidCoins == Optional.of(Coin.PENNY);
    }

}
