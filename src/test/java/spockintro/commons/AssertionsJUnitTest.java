package spockintro.commons;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author bartosz walacik
 */
public class AssertionsJUnitTest {

    /**
     * KEY POINTS:
     * - JUnit built-in assertions are pain, fluent assertions
     *   are provided by external libraries (Fest, AssertJ, ...)
     *   but still, they are quite heavy
     * - usually you need to write custom error messages
     * - when assertion fails, you got whole stack trace
     */
    @Test
    @Ignore
    public void shouldAddTwoMoneyValues() {
        //given:
        Money money1 = new Money(0.5);
        Money money2 = new Money(0.5);

        //when:
        Money result = money1.add(money2);

        //then:
        Assert.assertEquals("money adding error", 1, result.getValue());
    }
}
