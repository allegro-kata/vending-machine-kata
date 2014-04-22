package spockintro.commons;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.ProductPOJO;
import pojo.ProductPOJOv2;
import pojo.ProductPOJOv3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroovyFeaturesInJavaTest {

    /**
     * KEY points
     * - have to write builder in all pojo classes
     * - long expression
     * - "Always remember we could hire a trained monkey to do your job!"
     */
    @Test
    public void shouldCorrectlyBuildProductPOJO() {

        // when:
        ProductPOJO product = ProductPOJO.Builder.productPOJO()
                                                    .withId(1L)
                                                    .withName("Candy")
                                                    .withPrice(new BigDecimal(2))
                                                    .build();

        //then:
        assertTrue(product.getId() == 1L);
        assertEquals(product.getName(), "Candy");
        assertEquals(product.getPrice(), new BigDecimal(2));
    }

    /**
     * KEY points:
     * - it's hard to find custom methods in all getters, setters, constructors ...
     */
    @Test
    public void shouldAddCompanyNameAtLeastOfProductName() {

        //when:
        ProductPOJOv2 product = new ProductPOJOv2("Candy");

        //then:
        assertEquals(product.getName(), "Candy, Arni Best Food");
    }


    /**
     * KEY points
     * - additional job in every change in POGO class
     * - more, and more auto generated (generally not tested) code
     */
    @Test
    public void toStringTest() {

        //when:
        ProductPOJO product = new ProductPOJO(1L, "Candy", new BigDecimal(2));

        then:
        assertEquals(product.toString(), "ProductPOGO(id:1, name:Candy, price:2)");
    }

    /**
     * KEY points
     * - baroque, brand new world in java 8
     */
    @Test
    public void shouldTransformProductName() {

        //given:
        ProductPOJOv3 product = new ProductPOJOv3("Candy");

        //when:
        String productName = product.getName(new Function<String, String>() {
            @Override
            public String apply(String name) {
                return "The best " + name;
            }
        });

        //then:
        assertEquals(productName, "The best Candy");
    }

    /**
     * KEY points
     * - inconsistent api
     */
    @Test
    public void shouldShowInconsistentJavaApi() {

        //when:
        String[] array = new String[0];
        Collection<String> collection = new ArrayList<>(0);
        String string = "";

        //then:
        assertTrue(array.length == 0);
        assertTrue(collection.size() == 0);
        assertTrue(string.length() == 0);
    }


    @Test
    public void shouldRegisterAllQuackableAnimalsSound() {

        //given:
        List<Quackable> quackers = Lists.newArrayList(new Duck(), new Frog());
        List<String> sounds = Lists.newArrayList();

        //when:
        for (Quackable q: quackers) {
            sounds.add(q.quack());
        }

        //then:
        assertEquals(sounds, Lists.newArrayList("I am a Duck", "I am a Frog"));

    }

    private class Duck implements Quackable {
        public String quack() { return "I am a Duck"; };
    }

    private class Frog implements Quackable {
        public String quack() { return  "I am a Frog"; }
    }

    private interface Quackable {
        String quack();
    }

    /**
     * KEY points
     * -
     */
    @Test
    public void shouldSumUpTwoLists() {
        //given:
        List<String > left = Lists.newArrayList("Hello");
        List<String> right = Lists.newArrayList("Spock");

        //when:
        List<String> newList = new ArrayList<>(left);
        newList.addAll(right);

        //then:
        assertEquals(newList, Lists.newArrayList("Hello", "Spock"));
    }

    /**
     * KEY points
     * - illegible test names
     * - problem with e2e tests
     */
    @Test
    public void afterOpenMainPageAndAddProductToBasketShouldBeAbleToSubmitOrderWith1ProductAndAfterSubmitShouldBeConfirmed() {

    }

}
