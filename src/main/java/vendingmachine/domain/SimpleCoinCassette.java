package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.exception.EmptyTubeException;
import vendingmachine.domain.exception.FullTubeException;

public class SimpleCoinCassette implements CoinCassette{

    private final long capacity;
    
    private final List<Coin> coinStore = new ArrayList<>(); 
    
    public SimpleCoinCassette(long capacity) {
        this.capacity = capacity;
    }
    
    @Override
    public boolean isFull(Coin coin) {
        return coinStore.size() == capacity; 
    }

    @Override
    public boolean isEmpty(Coin tube) {
        return coinStore.isEmpty(); 
    }

    @Override
    public void push(Coin toTube) {
        if (isFull(toTube)) {
            throw new FullTubeException();
        }
        coinStore.add(toTube);
    }

    @Override
    public void pop(Coin fromTube) {
        if (isEmpty(fromTube)) {
            throw new EmptyTubeException();
        }
        coinStore.remove(fromTube);
    }
    
}
