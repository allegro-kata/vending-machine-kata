package spockintro.commons;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author bartosz walacik
 */
public class BddSytleJunitTest {

    /**
     * KEY POINTS:
     * - long method names are hard to read
     * - BDD blocks are barely convention, if absent you got
     *   complete mess
     * - JUnit built-in assertions are pain, fluent assertions
     *   are provided by external libraries (Fest, AssertJ, ...)
     *   but still, they are quite heavy
     */
    @Test
    public void shouldHoldGivenMoneyValue(){
        //
        //given:
        BigDecimal givenValue =
                new BigDecimal(0.5).setScale(2, RoundingMode.HALF_UP);
        Money money = new Money(givenValue);

        //when:
        BigDecimal holdValue = money.getValue();

        //then:
        Assert.assertEquals(givenValue,holdValue);
    }
}