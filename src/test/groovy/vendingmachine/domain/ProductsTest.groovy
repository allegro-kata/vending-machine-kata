package vendingmachine.domain

import spock.lang.Specification
import spockintro.commons.Money

class ProductsTest extends Specification{

    def machine;

    def setup() {
        machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
    }

    def "when i insert enough money i get the selected product"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        when:
            money.each { machine.insert(it) }
            machine.choose(product) // ? dispensed product ?
            def display = machine.display
        then:
            display == "THANK YOU"
            machine.credit.isZero()
        when:
            def nextDisplay = machine.display
        then:
            nextDisplay == "INSERT A COIN"
        where:
        product | money
        Product.CANDY | [ Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKEL ]
        Product.CHIPS | [ Coin.QUARTER, Coin.QUARTER ]
        Product.COLA | [ Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER ]
    }

    def "when i insert not enough money a message is displayed"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def sum = new BigDecimal(0L)
        when:
            money.each {
                machine.insert(it)
                sum += it.value
            }
            machine.choose(product) // ? dispensed product ?
            def display = machine.display
        then:
            display == "PRICE $product.price.value"
            machine.credit.isZero() == false
        when:
            def nextDisplay = machine.display
        then:
            nextDisplay == "CREDIT $sum"
        where:
            product | money
            Product.CANDY | [ Coin.QUARTER, Coin.QUARTER, Coin.DIME ]
            Product.CHIPS | [ Coin.QUARTER ]
            Product.COLA | [ Coin.QUARTER, Coin.QUARTER, Coin.QUARTER ]
    }

    def "when i selected product and give no money i see 'insert coins'"() {
        given:
            def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            def product = Product.CANDY
        when:
            machine.choose(product) // ? dispensed product ?
            def display = machine.display
        then:
            display == "PRICE $product.price.value"
        when:
            def nextDisplay = machine.display
        then:
            nextDisplay == "INSERT A COIN"
    }
}
