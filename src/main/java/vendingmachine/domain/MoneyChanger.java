package vendingmachine.domain;

import spockintro.commons.Money;

import java.util.List;

public interface MoneyChanger {

    List<Coin> change(Money money);
}