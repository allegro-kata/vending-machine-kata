package vendingmachine.domain

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

    def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

    // przed kazdym testem
    def setup() {

    }

    // przed wszystkimi testami
    def setupSpec() {

    }

    // po kazdym tescie
    def cleanup() {

    }

    // po wszystkich testach
    def cleanupSpec() {

    }

    def "should display 'insert a coin' when ready"() {

        expect:
        machine.display == "INSERT A COIN"
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        when:
        machine.insert(coin)

        then:
        machine.display == "CREDIT 0.05"

        where:
        coin << [Coin.NICKEL]
    }

    def "should accept series of valid coins and should display the Credit"() {
        when:
        machine.insert(coin)

        then:
        machine.display == "CREDIT $coin.value"

        where:
        coin << [Coin.DIME, Coin.NICKEL, Coin.QUARTER]
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        when:
        machine.insert(coin)

        then:
        machine.display == "INSERT A COIN"

        where:
        coin << [Coin.PENNY]
    }

    @Unroll
    def "should reject valid #coin coin when cassette tube is full"() {
        given:
        def casette = Stub(CoinCassette)    {
            isFull(_ as Coin) >> true
            push(_ as Coin) >> { throw new FullTubeException() }
        }
        def machine = new VendingMachine(Stub(ProductMagazine), casette)

        when:
        machine.insert(coin)

        then:
        machine.display == "CASSETTE IS FULL, SORRY"

        when:
        def disp = machine.display

        then:
        disp == "INSERT A COIN"

        where:
        coin << [Coin.DIME, Coin.NICKEL, Coin.QUARTER]
    }
}