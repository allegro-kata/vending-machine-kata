package vendingmachine.domain;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.util.ArrayList;
import java.util.List;

import static vendingmachine.domain.Coin.DIME;
import static vendingmachine.domain.Coin.NICKEL;
import static vendingmachine.domain.Coin.PENNY;
import static vendingmachine.domain.Coin.QUARTER;

public class VendingMachine {
    private Money credit;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private String display = "INSERT A COIN";

    public VendingMachine(ProductMagazine magazine, CoinCassette cassette) {
        Preconditions.checkArgument(magazine != null, "magazine can't be null");
        Preconditions.checkArgument(cassette != null, "cassette can't be null");
        this.cassette = cassette;
        this.magazine = magazine;
        this.credit = new Money(0);
    }

    public void getProduct(Product product) throws EmptyShelfException {
        if (getCredit().isZero()) {
            display = "INSERT A COIN";
        } else if (getCredit().getValue().compareTo(product.getPrice().getValue()) < 0) {
            display = "PRICE " + product.getPrice().format();
        } else {
            credit = new Money(credit.getValue().subtract(product.getPrice().getValue()));
            magazine.getItem(product);
            display = "THANK YOU";
        }
    }

    public List<Coin> getChange() {
        List<Coin> change = new ArrayList<>();
        while (!credit.isZero()) {
            if (getCoin(QUARTER).isPresent()) {
                change.add(QUARTER);
            } else if (getCoin(DIME).isPresent()) {
                change.add(DIME);
            } else if (getCoin(NICKEL).isPresent()) {
                change.add(NICKEL);
            }
        }
        return change;
    }

    private Optional<Coin> getCoin(Coin coin) {
        if (credit.getValue().compareTo(coin.getValue()) > -1) {
            credit = new Money(credit.getValue().subtract(coin.getValue()));
            return Optional.of(coin);
        }
        return Optional.absent();
    }

    /**
     * When valid coin is inserted, machine accepts it and updates credit.
     * Machine rejects {@link Coin#PENNY}
     *
     * @return rejected coin
     */
    public Optional<Coin> insert(Coin coin) {
        Preconditions.checkArgument(coin != null, "coin can't be null");
        if (cassette.isFull(coin)) {
            display = "CASSETTE IS FULL, SORRY";
            return Optional.of(coin);
        }
        if (coin == PENNY) {
            return Optional.of(PENNY);
        }
        coinAccepted(coin);
        if (getCredit().isZero()) {
            display = "INSERT A COIN";
        } else {
            display = "CREDIT " + getCredit().getValue();
        }
        return Optional.absent();
    }

    public String getDisplay() {
        return display;
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    private Money getCredit() {
        return credit;
    }

    private void coinAccepted(Coin coin) {
        cassette.push(coin);
        credit = credit.add(coin.getMoney());
    }

    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
    return Collections.unmodifiableList(coinReturnTray);
    }*/
}
