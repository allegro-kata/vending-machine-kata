package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by mariusz.kopylec on 2014-05-07.
 */
class ProductSelectionTest extends Specification {

    @Unroll
    def "should get #product and display 'THANK YOU' when client inserts #coins"() {
        given:
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        coins.each { machine.insert(it) }
        machine.getProduct(product);

        then:
        machine.display == "THANK YOU"

        where:
        product       | coins
        Product.CANDY | [Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKEL]
        Product.COLA  | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]
        Product.CHIPS | [Coin.QUARTER, Coin.QUARTER]
    }

    @Unroll
    def "should not get #product and display 'PRICE #product.price.value' when client inserts #coins"() {
        given:
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        coins.each { machine.insert(it) }
        machine.getProduct(product);

        then:
        machine.display == "PRICE $product.price.value"

        where:
        product       | coins
        Product.CANDY | [Coin.QUARTER, Coin.QUARTER, Coin.DIME]
        Product.COLA  | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]
        Product.CHIPS | [Coin.QUARTER]
    }

    @Unroll
    def "should not get #product and display 'INSERT A COINS' when client doesn't insert any coins"() {
        given:
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        machine.getProduct(product);

        then:
        machine.display == "INSERT A COIN"

        where:
        product << [Product.CANDY, Product.CHIPS, Product.COLA]
    }
}
