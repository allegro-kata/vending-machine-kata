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
    def "should accept one valid #coin and display its value as Credit"(Coin coin, Optional<Coin> result, display) {
        when:
            def vendingMachine = new VendingMachine(Mock(ProductMagazine), Mock(CoinCassette))
        then:
            result == vendingMachine.insert(coin)
            display == vendingMachine.getDisplay()
        where:
            coin       | result                 | display
            Coin.DIME  | Optional.absent()      | "CREDIT 0.10"
            Coin.PENNY | Optional.of(Coin.PENNY)| "INSERT A COIN"
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
            def expectedValue = Coin.DIME.value + Coin.DIME.value
            def vendingMachine = new VendingMachine(Mock(ProductMagazine), Mock(CoinCassette))
        when:
            vendingMachine.insert(Coin.DIME)
            vendingMachine.insert(Coin.DIME)
        then:
            vendingMachine.display == "CREDIT " + expectedValue
    }

    
    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
            def vendingMachine = new VendingMachine(Mock(ProductMagazine), Mock(CoinCassette))
        when:
            vendingMachine.insert(Coin.DIME)
            vendingMachine.insert(Coin.PENNY)
        then:
            vendingMachine.display == "CREDIT 0.10"
    }

    def "should reject coin when cassete is full"() {
        given:
            def coinCassette = Stub(CoinCassette) 
            coinCassette.push(_ as Coin) >> { throw new FullTubeException() }
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        when:
            def result = vendingMachine.insert(Coin.DIME)
        then: 
            result == Optional.of(Coin.DIME)
    }

    def "should display info when reached max capacity"() {
        given:
            def coinCassette = Stub(CoinCassette) 
            coinCassette.push(_ as Coin) >> { throw new FullTubeException() }
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        when:
            vendingMachine.insert(Coin.DIME)
        then: 
            vendingMachine.display == "CASSETTE IS FULL, SORRY"
    }

    def "should remove warning from display after second invocation"() {
        given:
            def coinCassette = Stub(CoinCassette) 
            coinCassette.push(_ as Coin) >> { throw new FullTubeException() }
            def vendingMachine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        when:
            vendingMachine.insert(Coin.DIME)
            vendingMachine.display
        then: 
        vendingMachine.display == "INSERT A COIN"
    }
    
}
