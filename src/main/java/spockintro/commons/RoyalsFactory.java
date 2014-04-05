package spockintro.commons;

/**
 * @author bartosz walacik
 */
public class RoyalsFactory {

    /**
     * Should create Royal Family tree:
     * <pre>
     *         Queen Elizabeth
     *         |               \
     *         Prince Charles  Diana
     *        /            \
     *     Prince William  Prince Harry
     *
     *
     * </pre>
     */
    public static Royals createRoyalFamily() {
        NameDecorator nameDecorator = new DefaultNameDecorator();
        Royals queen = new Royals(nameDecorator, "Queen Elizabeth");

        Royals charles = new Royals(nameDecorator, "Prince Charles");
        queen.addChild(charles);
        queen.addChild(new Royals(nameDecorator, "Diana"));

//        charles.addChild(new Royals("Prince William"));
//        charles.addChild(new Royals("Prince Harry"));
        return queen;
    }
}
