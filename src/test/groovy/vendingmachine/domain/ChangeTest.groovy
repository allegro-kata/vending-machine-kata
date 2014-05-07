package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by mariusz.kopylec on 2014-05-07.
 */
class ChangeTest extends Specification {

    @Unroll
    def "should get #product and display 'THANK YOU' when client inserts #coins"() {
        given:
        ProductMagazine magazine = Mock();
        VendingMachine machine = new VendingMachine(magazine, Stub(CoinCassette))

        when:
        coins.each { machine.insert(it) }
        machine.getProduct(product);

        then:
        machine.display == "THANK YOU"
        1 * magazine.getItem(product);
        machine.change == change

        where:
        product       | coins                                                                                || change
        Product.CANDY | [Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKEL, Coin.NICKEL]                    || [Coin.NICKEL]
        Product.COLA  | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER] || [Coin.QUARTER, Coin.QUARTER]
        Product.CHIPS | [Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.QUARTER, Coin.NICKEL]                   || [Coin.QUARTER, Coin.DIME, Coin.NICKEL]
    }
}
