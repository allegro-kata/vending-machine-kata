package vendingmachine.domain

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by mariusz.kopylec on 2014-05-07.
 */
class CassetteTest extends Specification {

    @Unroll
    def "should reject a valid #coin when internal Coin Cassette is full"() {
        given:
        CoinCassette cassette = Stub() {
            isFull(coin) >> true
        }
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), cassette)

        when:
        def rejectedCoin = machine.insert(coin);

        then:
        rejectedCoin.isPresent()
        rejectedCoin.get() == coin

        where:
        coin << [Coin.DIME, Coin.QUARTER, Coin.NICKEL]
    }

    @Unroll
    def "should display 'CASSETTE IS FULL, SORRY' when internal #coin Coin Cassette is full"() {
        given:
        CoinCassette cassette = Stub() {
            isFull(coin) >> true
        }
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), cassette)

        when:
        machine.insert(coin);

        then:
        machine.display == "CASSETTE IS FULL, SORRY"

        where:
        coin << [Coin.DIME, Coin.QUARTER, Coin.NICKEL]
    }

    @Unroll
    def "should display credit when internal #rejectedCoin Coin Cassette is full and client inserts #insertedCoins"() {
        given:
        CoinCassette cassette = Stub() {
            isFull(rejectedCoin) >> true
        }
        VendingMachine machine = new VendingMachine(Stub(ProductMagazine), cassette)

        when:
        machine.insert(rejectedCoin);
        insertedCoins.each { machine.insert(it) }

        then:
        machine.display == "CREDIT $expectedCredit"

        where:
        rejectedCoin | insertedCoins               | expectedCredit
        Coin.NICKEL  | [Coin.DIME, Coin.QUARTER]   | 0.35
        Coin.DIME    | [Coin.NICKEL, Coin.QUARTER] | 0.30
        Coin.QUARTER | [Coin.DIME, Coin.NICKEL]    | 0.15
    }
}
