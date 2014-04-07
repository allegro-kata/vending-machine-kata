package spockintro.commons;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubJavaTest {

    /**
     * KEY POINTS:
     * - in mockito you can create stub using mock() method ;)
     */
    @Test
    public void shouldReturnNameWithCorrectPrefix() {

        //when:
        NameDecorator nameDecorator = mock(NameDecorator.class);
        when(nameDecorator.getPrefix()).thenReturn("mr ");

        Royals royals = new RoyalsDecorated(nameDecorator, "Harold");

        //then:
        assertEquals(royals.getName(), "mr Harold");
    }

    /**
     * KEY POINTS:
     * - for primitives types mockito return default values,
     * but for String, primitive types wrappers, arrays, collections, custom classes it return null
     */
    @Test
    public void shouldReturnDefaultValue() {

        //when:
        NameDecorator nameDecorator = mock(NameDecorator.class);
        when(nameDecorator.getPrefix()).thenReturn("");
        Royals royals = new RoyalsDecorated(nameDecorator, "Harold");

        //then:
        assertEquals(royals.getName(), "Harold");
    }

    /**
     * KEY POINTS:
     * - you have to teach stub to return default value to arrays, collections
     *   if you don't do this stub returns null
     */
    @Test
    public void shouldReturnDefaultValueForCollection() {

        //given:
        DummyListContainer dummyListContainer = mock(DummyListContainer.class);
        when(dummyListContainer.getList()).thenReturn(Collections.EMPTY_LIST);

        //when:
        List list = dummyListContainer.getList();

        //then:
        assertTrue(list.isEmpty());
    }

    /**
     * KEY POINTS:
     * - if you have to teach nested stub this doesn't look good
     */
    @Test
    public void shouldReturnDefaultValueForNestedObject() {

        //given:
        DummyListContainer dummyListContainer = mock(DummyListContainer.class);
        DummyListContainer nestedDummyListContainer = mock(DummyListContainer.class);

        when(nestedDummyListContainer.getList()).thenReturn(Collections.EMPTY_LIST);
        when(dummyListContainer.getDummyListContainer()).thenReturn(nestedDummyListContainer);

        //when:
        List list = dummyListContainer.getDummyListContainer().getList();

        //then:
        assertTrue(list.isEmpty());
    }

    private class DummyListContainer {
        List getList() {
            return null;
        }

        DummyListContainer getDummyListContainer() {
            return null;
        }
    }
}
