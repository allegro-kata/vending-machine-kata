package vendingmachine.domain.exceptions;

import vendingmachine.domain.Coin;

/**
 * @author bartosz walacik
 */
public class EmptyTubeException extends RuntimeException {
    public EmptyTubeException(Coin tube) {
        super("Tube " + tube + " is empty");
    }
}