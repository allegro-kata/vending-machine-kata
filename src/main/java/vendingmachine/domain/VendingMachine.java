package vendingmachine.domain;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.util.Collections;
import java.util.List;

import static vendingmachine.domain.Coin.PENNY;

public class VendingMachine {
    private final MoneyChanger moneyChanger;
    private Money credit;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private boolean isRecentlyUsedTubeFull;
    private boolean notEnoughMoney;
    private Product recentlySelectedProduct;

    public VendingMachine(ProductMagazine magazine, CoinCassette cassette, MoneyChanger moneyChanger) {
        Preconditions.checkArgument(magazine != null, "magazine can't be null");
        Preconditions.checkArgument(cassette != null, "cassette can't be null");
        this.cassette = cassette;
        this.magazine = magazine;
        this.moneyChanger = moneyChanger;
        this.credit = new Money(0);
        this.isRecentlyUsedTubeFull = false;
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

        this.isRecentlyUsedTubeFull = false;
        if (cassette.isFull(coin)) {
            this.isRecentlyUsedTubeFull = true;
            return Optional.of(coin);
        }

        coinAccepted(coin);
        return Optional.absent();

    }

    public String getDisplay() {
        if (notEnoughMoney) {
            return "PRICE " + recentlySelectedProduct.getPrice();
        }
        if (isRecentlyUsedTubeFull) {
            this.isRecentlyUsedTubeFull = false;
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

    private void coinAccepted(Coin coin){
        cassette.push(coin);
        credit = credit.add(coin.getMoney());
    }

    public Optional<Product> orderProduct(Product product) {
        if (credit.getValue().compareTo(product.getPrice().getValue()) < 0) {
            recentlySelectedProduct = product;
            notEnoughMoney = true;
        }
        if (!magazine.isEmpty(product)) {
            return Optional.of(product);
        }
        return null;
    }

    public List<Coin> order(Product product) {
        if (credit.getValue().compareTo(product.getPrice().getValue()) == 0) {
            return Collections.emptyList();
        }
        Money change = new Money(credit.getValue().subtract(product.getPrice().getValue()));
        return moneyChanger.change(change);
    }

    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
