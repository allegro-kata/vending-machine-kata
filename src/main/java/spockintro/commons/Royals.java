package spockintro.commons;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bartosz walacik
 */
public class Royals {
    private final String name;
    private final Set<Royals> children = new HashSet<>();
    private final NameDecorator nameDecorator;

    public Royals(NameDecorator nameDecorator, String name) {
        Preconditions.checkArgument(nameDecorator != null);

        this.nameDecorator = nameDecorator;
        this.name = name;
    }

    public String getName() {
        return nameDecorator.getPrefix() +  name;
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
