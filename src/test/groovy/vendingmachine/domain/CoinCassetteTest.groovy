package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

class CoinCassetteTest extends Specification {

    @Unroll
    def "should accept #coin when empty tube"() {

        given:
        def coinCassette = new CoinCassetteImpl()

        expect:
        coinCassette.isEmpty(coin)

        where:
        coin << [Coin.DIME, Coin.QUARTER, Coin.NICKEL]

    }

    def "should not accept coin when tube is full"() {

        given:
        def coinCassette = new CoinCassetteImpl()
        def coin = Coin.NICKEL
        coinCassette.setTubesCapacity(1)

        when:
        coinCassette.push(coin)

        then:
        coinCassette.isFull(coin)
    }

    def "should not accept coin when tube is full on vending machine"() {

        given:
        def coinCassette = new CoinCassetteImpl(tubesCapacity: 1)
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin = Coin.NICKEL
        machine.insert(coin)

        when:
        machine.insert(coin)

        then:
        machine.display == "CASSETTE IS FULL, SORRY"
    }

    def "should accept coin when 2 tube are not full on vending machine"() {

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

    def "should accept coin when tube is not full on vending machine"() {

        given:
        def coinCassette = new CoinCassetteImpl()
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin = Coin.QUARTER
        coinCassette.setTubesCapacity(1)

        when:
        machine.insert(coin)

        then:
        machine.display == "CREDIT $coin.value"
    }

    def "should display 'insert coins' after message 'cassette is full'"() {

        given:
        def coinCassette = new CoinCassetteImpl()
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)
        def coin = Coin.NICKEL
        coinCassette.setTubesCapacity(1)
        machine.insert(coin)

        when:
        machine.insert(coin)
        String afterInsertingFullCoin = machine.getDisplay()
        String secondDisplayCheck = machine.getDisplay()

        then:
        afterInsertingFullCoin == "CASSETTE IS FULL, SORRY"
        secondDisplayCheck == "INSERT COINS"
    }
}
