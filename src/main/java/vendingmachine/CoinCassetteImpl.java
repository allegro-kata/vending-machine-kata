package vendingmachine.domain;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

public class CoinCassetteImpl implements CoinCassette {

    private Map<Coin, Integer> tubes = new HashMap<>();

    public int getTubesCapacity() {
        return tubesCapacity;
    }

    public void setTubesCapacity(int tubesCapacity) {
        this.tubesCapacity = tubesCapacity;
    }

    private int tubesCapacity = 200;

    public CoinCassetteImpl() {
        this.tubes.put(Coin.DIME, 0);
        this.tubes.put(Coin.QUARTER, 0);
        this.tubes.put(Coin.NICKEL, 0);
    }

    @Override
    public boolean isFull(Coin tube) {
        Preconditions.checkArgument(tube != null, "Tube cannot be null");
        return (tubes.get(tube) == tubesCapacity);
    }

    @Override
    public boolean isEmpty(Coin tube) {
        Preconditions.checkArgument(tube != null, "Tube cannot be null");
        return (tubes.get(tube) == 0);
    }

    @Override
    public void push(Coin toTube) {
        int actualSize = tubes.get(toTube);
        actualSize++;
        tubes.put(toTube, actualSize);
    }

    @Override
    public void pop(Coin fromTube) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
