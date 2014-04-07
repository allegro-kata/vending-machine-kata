package spockintro.commons;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author bartosz walacik
 */
public class BddStyleJunitTest {

    /**
     * KEY POINTS:
     * - long method names are hard to read
     * - BDD blocks (given, when, then) are barely convention,
     *   if absent you got complete mess
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