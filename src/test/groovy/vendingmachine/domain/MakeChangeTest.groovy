package vendingmachine.domain

import spock.lang.Specification

class MakeChangeTest extends Specification {

    def "should not return change when credit equal to product price"() {
        given:
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette), Stub(MoneyChanger))
        4.times { machine.insert(Coin.QUARTER) }

        when:
        def change = machine.order(Product.COLA)

        then:
        !change
    }

    def "should return change when credit is greater then product price"() {
        given:
        def moneyChanger = Stub(MoneyChanger) {
            change(Coin.QUARTER.money) >> [Coin.QUARTER]
        }

        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette), moneyChanger)
        5.times { machine.insert(Coin.QUARTER) }

        when:
        def coins = machine.order(Product.COLA)

        then:
        coins == [Coin.QUARTER]
    }
}