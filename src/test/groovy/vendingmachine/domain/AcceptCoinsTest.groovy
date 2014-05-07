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
        returnValue  == excpectedValue

        where:
        coin         || excpectedValue
        Coin.NICKEL  || Optional.absent()
        Coin.DIME    || Optional.absent()
        Coin.QUARTER || Optional.absent()
    }

}
