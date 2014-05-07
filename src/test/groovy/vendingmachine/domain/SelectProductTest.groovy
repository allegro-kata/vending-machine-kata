package vendingmachine.domain

import spock.lang.Specification


class SelectProductTest extends Specification {

    def "should return product when product is available and enough money was put"() {
        given:
        def magazine = Stub(ProductMagazine) {
            isEmpty(_ as Product) >> false
        }
        def machine = new VendingMachine(magazine, Stub(CoinCassette))

        when:
        def cola = machine.orderProduct(Product.COLA)

        then:
        cola.isPresent()
        cola.get() == Product.COLA
    }

    def "should display product price when not enough money was put"() {
        given:
        def magazine = Stub(ProductMagazine) {
            isEmpty(_ as Product) >> false
        }
        def machine = new VendingMachine(magazine, Stub(CoinCassette))

        when:
        machine.orderProduct(Product.COLA)

        then:
        machine.getDisplay() == "PRICE $Product.COLA.price"
    }

    def "should increase money amount in cassette when user order product"() {
        given:
        def magazine = Stub(ProductMagazine) {
            isEmpty(_ as Product) >> false
        }

        def coinCassette = Mock(CoinCassette)
        def machine = new VendingMachine(magazine, coinCassette)

        when:
        4.times { machine.insert(Coin.QUARTER) }

        then:
        4 * coinCassette.push(Coin.QUARTER)
    }
}