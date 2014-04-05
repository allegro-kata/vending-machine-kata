package vendingmachine.domain;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;

import static vendingmachine.domain.Coin.PENNY;

public class VendingMachine {

    private Money credit;
    private String message;

    private final ProductMagazine magazine;
    private final CoinCassette cassette;

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
            return rejectInvalidCoin(coin);
        }

        if (!cassetteIsReady(coin)) {
            return rejectCoinCassetteIsFull(coin);
        } else{
            coinAccepted(coin);
            return Optional.absent();
        }
    }


    public String getDisplay() {
        if (pendingMessage()){
            return consumeMessage();
        }
        if (getCredit().isZero()) {
            return "INSERT A COIN";
        }

        return "CREDIT "+ getCredit().format();
    }

    private boolean cassetteIsReady(Coin coin) {
        return !cassette.isFull(coin);
    }

    private Optional<Coin> rejectInvalidCoin(Coin coin) {
        return Optional.of(coin);
    }

    private Optional<Coin> rejectCoinCassetteIsFull(Coin coin) {
        message = "CASSETTE IS FULL, SORRY";
        return Optional.of(coin);
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

    private boolean pendingMessage() {
        return message != null;
    }

    private String consumeMessage() {
        String m = message;
        message = null;
        return m;
    }
    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
