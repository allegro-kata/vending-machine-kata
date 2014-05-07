package vendingmachine.domain;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.math.BigDecimal;

import static vendingmachine.domain.Coin.PENNY;

public class VendingMachine {
    private Money credit;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private Boolean fullWarning = false;
    private Boolean priceWarning = false;
    private Boolean buyWarning = false;
    private BigDecimal selectedProductPrice;

    public VendingMachine(ProductMagazine magazine, CoinCassette cassette) {
        Preconditions.checkArgument(magazine != null, "magazine can't be null");
        Preconditions.checkArgument(cassette != null, "cassette can't be null");
        this.cassette = cassette;
        this.magazine = magazine;
        this.credit = new Money(0);
    }

    /**
     * When valid coin is inserted, machine accepts it and updates credit.
     * Machine rejects {@link Coin#PENNY}
     *
     * @return rejected coin
     */
    public Optional<Coin> insert(Coin coin){
        Preconditions.checkArgument(coin != null, "coin can't be null");

        if (coin == PENNY){
            return Optional.of(PENNY);
        }
        try {
            coinAccepted(coin);
        } catch (FullTubeException e) {
            fullWarning = true;
            return Optional.of(coin);
        }
        return Optional.absent();
    }

    public String getDisplay() {
        if (buyWarning) {
            buyWarning = false;
            return "THANK YOU";
        }
        if (priceWarning) {
            priceWarning = false;
            return "PRICE " + selectedProductPrice;
        }
        if (fullWarning) {
            fullWarning = false;
            return "CASSETTE IS FULL, SORRY";
        }
        if (getCredit().isZero()) {
            return "INSERT A COIN";
        }

        return "CREDIT "+ getCredit().format();
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    private Money getCredit() {
        return credit;
    }

    private void coinAccepted(Coin coin) throws FullTubeException {
        cassette.push(coin);
        credit = credit.add(coin.getMoney());
    }

    public Optional<Product> buy(Product product) {
        if (credit.getValue().compareTo(product.getPrice().getValue()) < 0) {
            selectedProductPrice = product.getPrice().getValue();
            priceWarning = true;
            return Optional.absent();
        }
        credit = new Money(credit.getValue().subtract(product.getPrice().getValue()));
        buyWarning = true;
        magazine.getItem(product);
        return Optional.of(product);
    }

    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
