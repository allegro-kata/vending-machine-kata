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
    private Boolean coinCassetteError = false;
    private Product selectedProduct;
    private Boolean notEnoughMoneyInserted = false;

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
        } catch (FullTubeException ex) {
            coinCassetteError = true;
        }
        return Optional.absent();
    }

    public String getDisplay() {
        if (getCredit().isZero()) {
            if (selectedProduct != null && notEnoughMoneyInserted == false) {
                notEnoughMoneyInserted = true;
                return "PRICE " + selectedProduct.getPrice().format();
            } else {
                notEnoughMoneyInserted = false;
                return "INSERT A COIN";
            }
        }

        if (coinCassetteError == true) {
            coinCassetteError = false;
            return "CASSETTE IS FULL, SORRY";
        }

        if (selectedProduct != null) {
            int enoughMoney = getCredit().getValue().compareTo(selectedProduct.getPrice().getValue());
            if (enoughMoney >= 0) {
                credit = new Money(0);
                selectedProduct = null;
                return "THANK YOU";
            } else if (notEnoughMoneyInserted == false) {
                notEnoughMoneyInserted = true;
                return "PRICE " + selectedProduct.getPrice().format();
            } else {
                notEnoughMoneyInserted = false;
            }
        }

        return "CREDIT "+ getCredit().format();
    }

    public void selectProduct(Product product) {
        selectedProduct = product;
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

    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
