package Model.Transition;

import Model.Entity;

import java.util.function.Consumer;

public class HaltAction extends ActionNode {

    public HaltAction(Consumer<Entity> action) {
        super(action);
    }

    @Override
    public TransitionNode apply(Entity entity) {
        this.action.accept(entity);
        return null;
    }

}
