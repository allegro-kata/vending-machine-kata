package spockintro.commons;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static spockintro.commons.RoyalsJAssert.findChild;

/**
 * @author bartosz walacik
 */
public class CustomAssertionJUnitTest {

    /**
     * KEY POINTS:
     *  - consider custom assertions when testing complex responses
     */
    @Test
    public void shouldCreateRoyalFamily(){
        //when
        Royals queen = RoyalsFactory.createRoyalFamily();

        //then
        Assertions.assertThat(queen.getName()).isEqualTo("Queen Elizabeth");
        Assertions.assertThat(queen.getChildren()).extracting("name").containsOnly("Prince Charles", "Diana");
        Royals charles = findChild(queen, "Prince Charles");
        Assertions.assertThat(charles).isNotNull();
        Assertions.assertThat(charles.getChildren()).extracting("name").containsOnly("Prince William", "Prince Harry");
    }

    /**
     * KEY POINTS:
     *  - custom assertions should use DSL
     */
    @Test
    public void shouldCreateRoyalFamilyCustomAssertion() {
        //when
        Royals queen = RoyalsFactory.createRoyalFamily();

        //then
        RoyalsJAssert.assertThat(queen)
                      .hasName("Queen Elizabeth")
                      .hasChildren("Diana", "Prince Charles")
                      .andChild("Prince Charles")
                      .hasChildren("Prince William", "Prince Harry");
    }
}
