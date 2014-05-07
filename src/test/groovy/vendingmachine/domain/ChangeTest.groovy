package vendingmachine.domain

import spock.lang.Specification

/**
 * Created by maciej.kowalski on 07.05.2014.
 */
class ChangeTest extends Specification{

    def "i get correct change after i buy a product"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            coins.each { machine.insert(it) }
        when:
            def change = machine.choose(product)
        then:
            change == expectedChange
        where:
        product       | coins | expectedChange
        Product.CANDY | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER] | [Coin.DIME]
        Product.CHIPS | [Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.DIME] | [Coin.QUARTER, Coin.DIME]
    }
}
