package spockintro.commons;

import com.google.common.base.Preconditions;

/**
 * @author bartosz walacik
 */
public class RoyalsDecorated extends Royals{
    private final NameDecorator nameDecorator;

    public RoyalsDecorated(NameDecorator nameDecorator, String name) {
        super(name);
        Preconditions.checkArgument(nameDecorator!=null);
        this.nameDecorator = nameDecorator;
    }


    @Override
    public String getName() {
        return nameDecorator.getPrefix() +  super.getName();
    }
}
