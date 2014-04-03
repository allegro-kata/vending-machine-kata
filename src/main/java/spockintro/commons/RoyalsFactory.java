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
        Royals queen = new Royals("Queen Elizabeth");

        Royals charles = new Royals("Prince Charles");
        queen.addChild(charles);
        queen.addChild(new Royals("Diana"));

        charles.addChild(new Royals("Prince William"));
        charles.addChild(new Royals("Prince Harry"));
        return queen;
    }
}
