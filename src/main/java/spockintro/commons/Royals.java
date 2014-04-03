package spockintro.commons;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bartosz walacik
 */
public class Royals {
    private final String name;
    private final Set<Royals> children = new HashSet<>();

    public Royals(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addChild(Royals child){
        children.add(child);
    }

    public Set<Royals> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    @Override
    public String toString() {
        return "Royals("+name+")";
    }
}
