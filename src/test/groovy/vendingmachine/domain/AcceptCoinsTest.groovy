package vendingmachine.domain

import com.google.common.base.Optional
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for user story
 * 1. Accept Coins
 *
 * @author bartosz walacik
 */
class AcceptCoinsTest extends Specification{

    def "should display 'insert a coin' when ready"() {
        given:
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))
        expect:        
            vendingMachine.display == "INSERT A COIN"
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"(Coin coin, Optional<Coin> result) {
        when:
            def vendingMachine = new VendingMachine(Mock(ProductMagazine), Mock(CoinCassette))
        then:
            result == vendingMachine.insert(coin)
        where:
            coin       | result
            Coin.DIME  | Optional.absent()
            Coin.PENNY | Optional.of(Coin.PENNY)
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
            def vendingMachine = new VendingMachine(Mock(ProductMagazine), Mock(CoinCassette))
        when:
            vendingMachine.insert(Coin.DIME)
            vendingMachine.insert(Coin.DIME)
        then:
            vendingMachine.display == "CREDIT 0.20"
    }

    @Igno
    def "should reject invalid coin and shouldn't change the Credit"() {

    }

}
