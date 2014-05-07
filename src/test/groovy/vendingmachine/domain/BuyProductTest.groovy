package vendingmachine.domain

import com.google.common.base.Optional
import com.google.common.collect.Lists
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class BuyProductTest extends Specification{

    def "should display price buy product when no proper amount inserted"() {
        given:
        ProductMagazine magazine = Mock();
        CoinCassette cassette = Mock();
        def vendingMachine = new VendingMachine(magazine, cassette);
        vendingMachine.buy(Product.CHIPS);
        vendingMachine.insert(Coin.QUARTER);

        when:
        def displayResult = vendingMachine.display;

        then:
        displayResult == 'PRICE 0.50';
        0 * magazine.getItem(Product.CHIPS);
    }

    def "should buy product when proper amount inserted"() {
        given:
        ProductMagazine magazine = Mock();
        CoinCassette cassette = Mock();
        def vendingMachine = new VendingMachine(magazine, cassette);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);

        when:
        def product = vendingMachine.buy(Product.CHIPS);
        def displayResult = vendingMachine.display

        then:
        product.get() == Product.CHIPS;
        displayResult == 'THANK YOU';
        1 * magazine.getItem(Product.CHIPS);
    }

    def "should correct credits after product bought"() {
        given:
        ProductMagazine magazine = Mock();
        CoinCassette cassette = Mock();
        def vendingMachine = new VendingMachine(magazine, cassette);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);

        when:
        vendingMachine.buy(Product.CHIPS);
        vendingMachine.display;
        def displayResult = vendingMachine.display;

        then:
        displayResult == 'INSERT COINS';
        1 * magazine.getItem(Product.CHIPS);
    }

    def "should re credits after product bought"() {
        given:
        ProductMagazine magazine = Mock();
        CoinCassette cassette = Mock();
        def vendingMachine = new VendingMachine(magazine, cassette);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.QUARTER);
        vendingMachine.insert(Coin.NICKEL);
        vendingMachine.insert(Coin.NICKEL);

        when:
        List<Coin> backMoney = vendingMachine.buyMoneyBack(Product.CHIPS);

        then:
        backMoney.equals(Lists.newArrayList(Coin.QUARTER, Coin.DIME));
    }

}
