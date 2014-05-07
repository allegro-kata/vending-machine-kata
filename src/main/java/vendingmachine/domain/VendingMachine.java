package vendingmachine.domain;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static vendingmachine.domain.Coin.PENNY;


public class VendingMachine {
    private Money credit;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private String errorString;

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
            errorString = "CASSETTE IS FULL, SORRY";
            return Optional.of(coin);
        }
        return Optional.absent();
    }

    public String getDisplay() {
        if (errorString != null) {
            String returnedString = errorString;
            errorString = null;
            return returnedString;
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

    /**
     * @param coin
     */
    private void coinAccepted(Coin coin) throws FullTubeException {
        cassette.push(coin);
        credit = credit.add(coin.getMoney());
    }

    public List<Coin> choose(Product product) {
        List change = new ArrayList();
        if (credit.getValue().compareTo( product.getPrice().getValue() ) >= 0) {
            errorString = "THANK YOU";
            magazine.getItem(product);

            BigDecimal changeAmount = credit.getValue().subtract(product.getPrice().getValue());
            credit = new Money(0);
            change = amountToCoins(changeAmount);
        } else {
            errorString = "PRICE "+product.getPrice().format();
        }
        return change;
    }

    protected List<Coin> amountToCoins(BigDecimal amount)
    {
        List<Coin> coins = new ArrayList();
        Coin[] change = {Coin.QUARTER, Coin.DIME, Coin.NICKEL};
        while (amount.compareTo(BigDecimal.ZERO) > 0) {
            for(Coin coin: change) {
                if (amount.subtract(coin.getValue()).compareTo(BigDecimal.ZERO) >= 0) {
                    coins.add(coin);
                    amount = amount.subtract(coin.getValue());
                    break;
                }
            }
        }
        return coins;
    }


    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
