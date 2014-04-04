package vendingmachine.domain

import spock.lang.Specification
import vendingmachine.domain.VendingMachine

class VendingMachineTest extends Specification {

    def "should display 'insert a coin' when ready"() {
        when:
        def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        then:
        vendingMachine.display == "INSERT A COIN"
        vendingMachine.balance.value == 0
        !vendingMachine.coinReturnTray
    }
}