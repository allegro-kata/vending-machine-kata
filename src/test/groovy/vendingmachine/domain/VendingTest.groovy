package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for user story
 * 1. Vending tests
 *
 * @author bartosz walacik
 */
class VendingTest extends Specification{

    def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

    /*
    @Unroll
    def "should product #product be dispensed when enough money (#price) is inserted and button is pressed"() {

        when:
        machine.insert(coins)

        then:
        //credit >= product.price
        1 * magazine.getItem(selectedProduct)

        machine.display == "THANK YOU"

        where:
        product       | coins
        Product.COLA  | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER]
        Product.CANDY | [Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKEL]
        Product.CHIPS | [Coin.QUARTER, Coin.QUARTER]
    }
*/
    @Unroll
    def "should change be returned when too much money was inserted"() {
        given:
        def casette = Stub(CoinCassette)    {
            isEmpty(_ as Coin) >> false
        }
        def machine = new VendingMachine(Stub(ProductMagazine), casette)

        when:
        3.times {
            machine.insert(Coin.QUARTER)
        }
        machine.buy(Product.CANDY)
        def change = machine.getCoinReturnTray()

        then:
        change == [Coin.DIME]
    }

}