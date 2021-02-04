package Model.Transition;

import Model.Entity;

import java.util.function.Consumer;

public abstract class ActionNode extends TransitionNode {

    Consumer<Entity> action;

    public ActionNode(Consumer<Entity> action){
        this.action = action;
    }
}
