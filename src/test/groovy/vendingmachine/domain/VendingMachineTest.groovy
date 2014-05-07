package vendingmachine.domain

import spock.lang.Specification

/**
 * Created by Wojciech.Rezulak on 2014-05-07.
 */
class VendingMachineTest extends Specification {

    VendingMachine vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

    def "should display selected product price on empty credit and insert coins msg after it"() {
        when:
        vendingMachine.selectProduct(product)

        then:
        vendingMachine.display == expectedValue
        vendingMachine.display == "INSERT A COIN"

        where:
        product         | expectedValue
        Product.CANDY   | "PRICE $Product.CANDY.price.value"
        Product.CHIPS   | "PRICE $Product.CHIPS.price.value"
        Product.COLA    | "PRICE $Product.COLA.price.value"
    }

    def "should display selected product price and current credit after it"() {
        when:
        vendingMachine.insert(Coin.QUARTER)
        vendingMachine.selectProduct(product)

        then:
        vendingMachine.display == expectedValue
        vendingMachine.display == "CREDIT $Coin.QUARTER.value"

        where:
        product         | expectedValue
        Product.CANDY   | "PRICE $Product.CANDY.price.value"
        Product.CHIPS   | "PRICE $Product.CHIPS.price.value"
        Product.COLA    | "PRICE $Product.COLA.price.value"
    }

    def "should dispense selected product when enough money has been inserted"() {
        when:
        def insertedAmount = 0;
        while (insertedAmount < productPrice) {
            vendingMachine.insert(Coin.QUARTER)
            insertedAmount += Coin.QUARTER.value
        }
        vendingMachine.selectProduct(product)

        then:
        vendingMachine.display == "THANK YOU"
        vendingMachine.display == "INSERT A COIN"

        where:
        product         | productPrice
        Product.CANDY   | Product.CANDY.price.value
        Product.CHIPS   | Product.CHIPS.price.value
        Product.COLA    | Product.COLA.price.value
    }

}
