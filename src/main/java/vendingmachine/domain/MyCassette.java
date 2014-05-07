package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public class MyCassette implements CoinCassette{
    private static final long CASSETTE_CAPACITY = 10;
    Map<String, Long> state = new HashMap<>();


    @Override
    public boolean isFull(Coin tube) {
        if (isEmpty(tube)) {
            return false;
        }
        return state.get(tube.name()) >= CASSETTE_CAPACITY;
    }

    @Override
    public boolean isEmpty(Coin tube) {
        return !state.containsKey(tube.name());
    }

    @Override
    public void push(Coin toTube) throws FullTubeException {
        if (isFull(toTube)) {
            throw new FullTubeException();
        }

        if (isEmpty(toTube)) {
            state.put(toTube.name(), 1L);
        } else {
            Long value = state.get(toTube.name());
            value++;
            state.put(toTube.name(), value);
        }
    }

    @Override
    public void pop(Coin fromTube) {

    }
}
