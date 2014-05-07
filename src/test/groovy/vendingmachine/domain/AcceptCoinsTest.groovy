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
        machine.insert(Coin.NICKEL)

        then:
        machine.display == "CREDIT 0.05"
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

}