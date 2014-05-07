package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

class CoinCassetteTest extends Specification {

    @Unroll
    def "should accept #coin when empty tube"(){

        given:
        def coinCassette = new CoinCassetteImpl()

        when:
        def result = coinCassette.isEmpty(coin)

        then:
        result == expectedValue

        where:
        coin         | expectedValue
        Coin.DIME    | true
        Coin.QUARTER | true
        Coin.NICKEL  | true

    }

    def "should not accept coin when tube is full"(){

        given:
        def coinCassette = new CoinCassetteImpl()
        def coin = Coin.NICKEL

        when:
        coinCassette.setTubesCapacity(1)
        coinCassette.push(coin)
        def result = coinCassette.isFull(coin)

        then:
        result == true
    }

    def "should not accept coin when tube is full on vending machine"(){

        given:
        def coinCassette = new CoinCassetteImpl()
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin = Coin.NICKEL

        when:
        coinCassette.setTubesCapacity(1)
        machine.insert(coin)
        machine.insert(coin)

        then:
        machine.display == "CASSETTE IS FULL, SORRY"
    }

    def "should accept coin when 2 tube are not full on vending machine"(){

        given:
        def coinCassette = new CoinCassetteImpl()
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin1 = Coin.NICKEL
        def coin2 = Coin.QUARTER
        def sumCoin = coin1.value + coin2.value

        when:
        coinCassette.setTubesCapacity(1)
        machine.insert(coin1)
        machine.insert(coin2)

        then:
        machine.display == "CREDIT $sumCoin"
    }

    def "should accept coin when tube is not full on vending machine"(){

        given:
        def coinCassette = new CoinCassetteImpl()
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin = Coin.QUARTER

        when:
        coinCassette.setTubesCapacity(1)
        machine.insert(coin)

        then:
        machine.display == "CREDIT $coin.value"
    }
}
