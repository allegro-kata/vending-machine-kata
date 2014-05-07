package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine
import vendingmachine.domain.Product
import vendingmachine.domain.ProductMagazine
import vendingmachine.domain.CoinCassette
import vendingmachine.domain.Coin

class ProductSelectionTest extends Specification {

    def "client should be able to select product"() {
        given:
            def selectedProduct = Product.COLA
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        when:
            vendingMachine.selectProduct(selectedProduct)
        then:
            vendingMachine.display == 'PRICE $' + selectedProduct.getPrice().format()
    }
    
    def "should reduce required amount of money after inserting coins"() {
        given:
            def selectedProduct = Product.COLA
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            vendingMachine.selectProduct(selectedProduct)
        when:
            vendingMachine.insert(Coin.DIME)
        then:
            vendingMachine.display == 'PRICE $0.90' 
    }

    def "should display thank you when everything paid"() {
        given:
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            vendingMachine.selectProduct(Product.CHIPS)
        when:
            2.times { vendingMachine.insert(Coin.QUARTER) }
            vendingMachine.dispense()
        then:
            vendingMachine.display == 'THANK YOU' 
    }
    
    def "return change after product is dispensed"() {
        given:
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
            vendingMachine.selectProduct(Product.CHIPS)
            3.times { vendingMachine.insert(Coin.QUARTER) }
            vendingMachine.dispense()
        when:
            def change = vendingMachine.returnCoins()
        then:
            change == [Coin.QUARTER]
    }

}

