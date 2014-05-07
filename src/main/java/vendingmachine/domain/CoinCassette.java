package vendingmachine.domain;

import com.google.common.collect.Multimap;

/**
 * This is the place, where coins are collected.
 * 4-tube based coin changer able to store 4 coin types.
 * Tube has limited capacity, typically 200.
 * <br/>
 *
 * Full cassette can be easily replaced with empty one by serviceman.
 * <br/>
 *
 * Impl. hint: consider using {@link Multimap}
 *
 * @author bartosz walacik
 */
public interface CoinCassette {
    boolean isFull(Coin tube);

    boolean isEmpty(Coin tube);

    /**
     * @throws FullTubeException
     */
    void push(Coin toTube) throws FullTubeException;

    /**
     * @throws EmptyTubeException
     */
    void pop(Coin fromTube)throws EmptyTubeException;
}
