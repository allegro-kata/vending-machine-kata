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
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        expect:
        vendingMachine.getDisplay() == "INSERT A COIN"

    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {

        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        vendingMachine.insert(coin)

        then:
        vendingMachine.display == "CREDIT $coin.value"

        where:
        coin << [Coin.DIME, Coin.NICKEL, Coin.QUARTER]
    }

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


    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        def coinVal = Coin.PENNY

        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        def result = vendingMachine.insert(coinVal)

        then:

        vendingMachine.getDisplay() == "INSERT A COIN"
        result == Optional.of(Coin.PENNY)

    }

    @Unroll
    def "should display 'thank you' after choosing #product"() {

        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        5.times {
            vendingMachine.insert(coin)
        }

        when:
        vendingMachine.pushButton(product)

        then:
        vendingMachine.display == "THANK YOU"

        where:
        product       | coin
        Product.COLA  | Coin.QUARTER
        Product.CHIPS | Coin.QUARTER
        Product.CANDY | Coin.QUARTER

    }

    def "should not display 'thank you' after choosing #product"() {

        given:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        quantity.times {
            vendingMachine.insert(coin)
        }
        def coinSum = quantity * coin.getValue()

        when:
        vendingMachine.pushButton(product)

        then:
        vendingMachine.display == "CREDIT " + coinSum

        where:
        product       | coin        | quantity
        Product.COLA  | Coin.DIME   | 1
        Product.CHIPS | Coin.NICKEL | 2
        Product.CANDY | Coin.DIME   | 3

    }

    @Ignore
    def "should display 'insert coins' when display checked again"() {

        given:
        def coin = Coin.QUARTER
        def product = Product.CANDY
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        5.times {
            vendingMachine.insert(coin)
        }
        vendingMachine.pushButton(product)

        when:
        vendingMachine.display

        then:
        vendingMachine.display == "INSERT COINS"
    }

}
