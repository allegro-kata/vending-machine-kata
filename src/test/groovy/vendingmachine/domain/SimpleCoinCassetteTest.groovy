
package vendingmachine.domain

import spock.lang.Specification

class SimpleCoinCassetteTest extends Specification {
    
    static CAPACITY = 1

    def "should throw exception when capacity is reached"() {
        given:
            def coinCassette = new SimpleCoinCassette(CAPACITY)
        when:
            coinCassette.push(Coin.PENNY)
            coinCassette.push(Coin.PENNY)
        then:        
            thrown FullTubeException
    }
    
    def "should report that tube is empty for new cassette"() {
        when:
            def coinCassette = new SimpleCoinCassette(CAPACITY)
        then:
            coinCassette.isEmpty() == true
    }
    
    def "should throw exceotion when try to pop from empty casette"() {
        given:
            def coinCassette = new SimpleCoinCassette(CAPACITY)
        when:
            coinCassette.pop(Coin.PENNY)
        then:
            thrown EmptyTubeException
    }
    
    def "should remove coin from tue after poping"() {
        given:
            def coinCassette = new SimpleCoinCassette(CAPACITY)
        when:
            coinCassette.push(Coin.PENNY)
            coinCassette.pop(Coin.PENNY)
            coinCassette.pop(Coin.PENNY)
        then:
            thrown EmptyTubeException
    }
}

