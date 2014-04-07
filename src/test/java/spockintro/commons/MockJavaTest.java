package spockintro.commons;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockJavaTest {

    /**
     * KEY POINTS:
     * - have to add mockito
     */
    @Test
    public void shouldVerifyInteractionsWithCollaborators() {

        //given:
        NameDecorator nameDecorator = mock(NameDecorator.class);
        when(nameDecorator.getPrefix()).thenReturn("mr");

        Royals royals = new RoyalsDecorated(nameDecorator, "Harold");

        //when:
        royals.getName();

        //then:
        verify(nameDecorator, times(1)).getPrefix();
    }
}
