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
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        def message = machine.getDisplay()

        then:
        message == 'INSERT A COIN'
    }

    @Unroll
    def "should accept one valid #coin and display its value as Credit"() {
        given:
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        machine.insert(coin)

        then:
        machine.getDisplay() == message

        where:
        coin         | message
        Coin.NICKEL  | 'CREDIT 0.05'
        Coin.DIME    | 'CREDIT 0.10'
        Coin.QUARTER | 'CREDIT 0.25'
    }

    def "should accept series of valid coins and should display the Credit"() {
        given:
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        machine.insert(Coin.DIME)
        machine.insert(Coin.DIME)
        machine.insert(Coin.QUARTER)

        then:
        machine.getDisplay() == 'CREDIT 0.45'
    }

    def "should reject invalid coin and shouldn't change the Credit"() {
        given:
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        def returnValue = machine.insert(Coin.PENNY)

        then:
        returnValue  == Optional.of(Coin.PENNY)
        machine.display == 'INSERT A COIN'
    }

    @Unroll
    def "should not reject valid coin #coin"() {
        given:
        def machine = new VendingMachine(Stub(ProductMagazine), Stub(CoinCassette))

        when:
        def returnValue = machine.insert(coin)

        then:
        returnValue  == Optional.absent()

        where:
        coin << [Coin.NICKEL, Coin.DIME, Coin.QUARTER]
    }

    def "should display 'CASSETTE IS FULL, SORRY' when specific tube is full"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(Coin.QUARTER) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)

        when:
        machine.insert(Coin.QUARTER)

        then:
        machine.getDisplay() == 'CASSETTE IS FULL, SORRY'
    }

    def "should reject coin when specific tube is full"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(Coin.QUARTER) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette)

        when:
        def rejected = machine.insert(Coin.QUARTER)

        then:
        rejected.isPresent()
        rejected.get() == Coin.QUARTER
    }

    def "should display 'INSERT COINS' when display is checked again and specific tube is full and credit is zero"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(Coin.QUARTER) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette, Stub(MoneyChanger))
        machine.insert(Coin.QUARTER)
        machine.getDisplay()

        when:
        def display = machine.getDisplay()

        then:
        display == 'INSERT A COIN'
    }

    def "should display credit when display is checked again and specific tube is full and credit is not zero"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(Coin.QUARTER) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette, Stub(MoneyChanger))
        machine.insert(Coin.DIME)
        machine.insert(Coin.QUARTER)
        machine.getDisplay()

        when:
        def display = machine.getDisplay()

        then:
        display == 'CREDIT 0.10'
    }

    def "should display credit when inject coin to not full tube after displaying sorry message"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(Coin.QUARTER) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette, Stub(MoneyChanger))
        machine.insert(Coin.QUARTER)

        when:
        machine.insert(Coin.DIME)

        then:
        machine.getDisplay() == 'CREDIT 0.10'
    }

    @Ignore
    def "should always display 'ALL CASSETTES ARE FULL, SORRY' when all tubes are full"() {
        given:
        def coinCassette = Stub(CoinCassette) {
            isFull(_) >> true
        }
        def machine = new VendingMachine(Stub(ProductMagazine), coinCassette, Stub(MoneyChanger))
        machine.insert(Coin.QUARTER)
        machine.getDisplay()

        when:
        def display = machine.getDisplay()

        then:
        display == 'ALL CASSETTES ARE FULL, SORRY'
    }


}
