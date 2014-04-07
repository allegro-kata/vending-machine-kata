package spockintro.commons;

import org.junit.Test;

import static org.junit.Assert.fail;
import static com.googlecode.catchexception.CatchException.*;

public class VerifyExceptionsJUnitTest {

    /**
     * KEY POINTS:
     * - try catch - so baroque, so ugly
     * - hard to BDD, don't know where put 'then' block
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryToRoundNull() {

        try {
            //when:
            Money.round2(null);
            fail();
        } /* then ??? */ catch (IllegalArgumentException ex) {
            //as expect
        }
    }

    /**
     * KEY POINTS:
     * - How do I catch an exception thrown by a static method?
     *   Unfortunately, catch-exception does not support this. Fall back on try/catch blocks.
     * - Catch-exception does not provide an API to to catch exceptions that are thrown by constructors
     *   Use builder
     * - You have to add additional library
     * - If you want to catch both throwables and exceptions have a look at the catch-throwable packages
     */
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryToRoundNull2() {
        //given
        Money money = new Money(1);

        //when
//      catchException(Money.round2(null)); - static, sry ;(
        catchException(money).add(null);

        //then
        IllegalArgumentException exception = caughtException();
        assert exception.getMessage() == "Operation component cannot be null";
    }
}
