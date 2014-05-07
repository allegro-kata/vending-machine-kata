package vendingmachine.domain;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static vendingmachine.domain.Coin.PENNY;

public class VendingMachine {
    private Money credit;
    private String errorMessage;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private List<Coin> coinReturnTray;

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

        if (coinAccepted(coin)) {
            return Optional.absent();
        } else{
            return Optional.of(coin);
        }

    }

    public String getDisplay() {
        if (!errorMessage.isEmpty()) {
            String result = errorMessage;
            errorMessage = "";
            return result;
        } else if (getCredit().isZero()) {
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

    private boolean coinAccepted(Coin coin){
        try {
            cassette.push(coin);
            credit = credit.add(coin.getMoney());
            return true;
        } catch(FullTubeException e) {
            errorMessage = "CASSETTE IS FULL, SORRY";
            return false;
        }
    }

    public void buy(Product product) {

        // byuing part

        // change calculation part
        BigDecimal moneyToReturn = getCredit().getValue().subtract(product.getPrice().getValue());
        Coin[] coins = {Coin.QUARTER, Coin.DIME, Coin.NICKEL, Coin.PENNY};

        while (moneyToReturn.compareTo(Coin.QUARTER.getValue()) >= 0 && !cassette.isEmpty(Coin.QUARTER)) {
            moneyToReturn -=  Coin.QUARTER.getValue()
            coinReturnTray.add(Coin.QUARTER)

        }

        // iterate on DIMEs

        // iterate on NICKELs

        // iterate on PENNIEs

    }

    /**
     * @return unmodifiableList
     */
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }
}
