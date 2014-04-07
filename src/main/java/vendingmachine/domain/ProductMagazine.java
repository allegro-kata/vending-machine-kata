package vendingmachine.domain;

import com.google.common.collect.Multimap;

/**
 * This is the place, where products are stored.
 * Magazine has 3 shelves for 3 types of products.
 * Shelve has limited capacity, typically 15.
 * <br/>
 *
 * Empty shelve can be easily filled with new items by serviceman
 * <br/>
 *
 * Impl. hint: consider using {@link Multimap}
 *
 * @author bartosz walacik
 */
public interface ProductMagazine {

    boolean isEmpty(Product shelve);

    /**
//     * @param EmptyShelveException
     */
    void getItem(Product fromShelve);
}
