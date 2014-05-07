package vendingmachine.domain;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import spockintro.commons.Money;
import static vendingmachine.domain.Coin.PENNY;

public class VendingMachine {
    private Money credit;
    private final ProductMagazine magazine;
    private final CoinCassette cassette;
    private String msg = "";
    private String lastMsg = "";

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

        if (coinAccepted(coin) == false) {
            msg = "CASSETTE IS FULL, SORRY";
        }

        return Optional.absent();
    }

    public String getDisplay() {
        if (lastMsg == "CASSETTE IS FULL, SORRY") {
            lastMsg = "";
            msg = "INSERT COINS";
            return msg;
        }
        if (getCredit().isZero()) {
            msg = "INSERT A COIN";
        }
        if (msg == "") {
            return "CREDIT " + getCredit().format();
        }

        lastMsg = msg;

        return msg;
    }

    /**
     * Current amount on display:
     * sum of *valid* coins inserted, minus sold products, minus change
     */
    private Money getCredit() {
        return credit;
    }

    private boolean coinAccepted(Coin coin){
        boolean isFull = cassette.isFull(coin);
        if (isFull) {
            return false;
        }
        cassette.push(coin);
        credit = credit.add(coin.getMoney());
        return true;
    }

    /**
     * @return unmodifiableList
     *
    public List<Coin> getCoinReturnTray() {
        return Collections.unmodifiableList(coinReturnTray);
    }*/
}
