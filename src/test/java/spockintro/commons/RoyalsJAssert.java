package spockintro.commons;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

/**
 * @author bartosz walacik
 */
public class RoyalsJAssert extends AbstractAssert<RoyalsJAssert, Royals> {

    private RoyalsJAssert(Royals actual) {
        super(actual, RoyalsJAssert.class);
    }

    public static RoyalsJAssert assertThat(Royals actual) {
        return new RoyalsJAssert(actual);
    }

    public RoyalsJAssert hasName(String expectedName) {
        Assertions.assertThat(actual.getName()).isEqualTo(expectedName);
        return this;
    }

    public RoyalsJAssert hasChildren(String... expectedNames) {
        Assertions.assertThat(actual.getChildren()).extracting("name").containsOnly(expectedNames);
        return this;
    }

    public RoyalsJAssert andChild(String expectedName) {
        Royals found = findChild(actual, expectedName);
        Assertions.assertThat(found).isNotNull();

        return new RoyalsJAssert(found);
    }

    public static Royals findChild(Royals parent, String expectedName){
        for (Royals ch : parent.getChildren()){
            if (ch.getName().equals(expectedName)){
                return ch;
            }
        }
        return null;
    }
}
