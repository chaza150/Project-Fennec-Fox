package Model.Transition;

import Model.Entity;

public abstract class TransitionNode {

    public abstract TransitionNode apply(Entity entity);

}
