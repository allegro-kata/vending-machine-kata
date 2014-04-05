package spockintro.commons;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * immutable
 */
public class Money {

    private final BigDecimal value;

    public Money(double value) {
        this.value = round2(value);
    }

    public Money(BigDecimal value) {
        checkArgument(value != null);
        this.value = round2(value);
    }


    public static Money money(double value){
        return new Money(value);
    }

    public Money add(Money that) {
        Preconditions.checkArgument(that != null, "Operation component cannot be null");

        return new Money(this.value.add(that.value));
    }

    @Override
    public String toString() {
        return "Money("+value+")";
    }

    public String format() {
        return value.toString();
    }

    public boolean isZero() {
        return this.equals(money(0));
    }

    /**
     * @return not null
     */
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Money)) {
            return false;
        }

        return hasSameValue((Money) object);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static BigDecimal round2(double value) {
        return round2(new BigDecimal(value));
    }

    public static BigDecimal round2(BigDecimal value) {
        Preconditions.checkArgument(value != null, "Value cannot be null");

        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private boolean hasSameValue(Money that) {
        return value.compareTo(that.value) == 0;
    }
}
