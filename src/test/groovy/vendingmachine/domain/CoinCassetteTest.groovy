package vendingmachine.domain

import spock.lang.Specification

public class CoinCassetteTest extends Specification{

    def "should display 'CASSETTE IS FULL' when inserting to many coins"() {
        given:
            def cassete = Stub(CoinCassette) {
                push(_) >> {
                    throw new FullTubeException()
                }
            }

            def machine = new VendingMachine(Stub(ProductMagazine), cassete)
        when:
            def returnedCoin = machine.insert(Coin.NICKEL)
            def display = machine.display
            def nextDisplay = machine.display
        then:
            returnedCoin.get() == Coin.NICKEL
            display == "CASSETTE IS FULL, SORRY"
            nextDisplay == "INSERT A COIN"
    }
}
