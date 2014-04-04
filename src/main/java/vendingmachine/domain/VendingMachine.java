package vendingmachine.domain;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.util.*;

import static spockintro.commons.Money.money;

public class VendingMachine {

    private final List<Coin> coinReturnTray = new ArrayList<>();
    private final ProductMagazine magazine;
    private final CoinCassette cassette;

    public VendingMachine(ProductMagazine magazine, CoinCassette cassette) {
        Preconditions.checkArgument(magazine != null, "magazine can't be null");
        Preconditions.checkArgument(cassette != null, "cassette can't be null");
        this.cassette = cassette;
        this.magazine = magazine;
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
