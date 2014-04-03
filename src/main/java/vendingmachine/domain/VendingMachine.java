package vendingmachine.domain;
import spockintro.commons.Money;

import java.util.*;

import static spockintro.commons.Money.money;

public class VendingMachine {

    private List<Coin> coinReturnTray;

    public VendingMachine() {
        coinReturnTray = new ArrayList<>();
    }

    public String getDisplay() {
        if (getBalance().isZero()) {
            return "INSERT A COIN";
        }

        return "zonk";
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    public Money getBalance() {
        return money(0);
    }

    /**
     * @return unmodifiableList
     */
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }
}
