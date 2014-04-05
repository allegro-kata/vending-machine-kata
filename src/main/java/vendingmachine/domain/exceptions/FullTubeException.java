package vendingmachine.domain.exceptions;

import vendingmachine.domain.Coin;

/**
 * @author bartosz walacik
 */
public class FullTubeException extends RuntimeException {
    public FullTubeException(Coin tube) {
        super("Tube " + tube + " is full");
    }
}
