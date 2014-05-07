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
class BuyProductTest extends Specification{

    def "should buy product when proper amount inserted"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette));

        when:
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.buy(Product.CHIPS);
        def displayResult = vendingMachine.display

        then:
        displayResult == 'THANK YOU'
    }

    def "should display price buy product when no proper amount inserted"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette));

        when:
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.buy(Product.CHIPS);
        def displayResult = vendingMachine.display

        then:
        displayResult == 'PRICE 0.50'
    }

    def "should correct credits after product bought"() {
        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette));

        when:
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.buy(Product.CHIPS);
        def displayResult = vendingMachine.display
        displayResult = vendingMachine.display

        then:
        displayResult == 'CREDIT 0.25'
    }


}
