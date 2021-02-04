package Model.Transition;

import Model.Entity;

import java.util.function.Consumer;

public class PassageAction extends ActionNode{

    TransitionNode resultantNode = null;

    public PassageAction(Consumer<Entity> action, TransitionNode resultantNode) {
        super(action);
        this.resultantNode = resultantNode;
    }

    public PassageAction(Consumer<Entity> action) {
        super(action);
    }

    public PassageAction addResultantNode(TransitionNode resultantNode){
        this.resultantNode = resultantNode;
        return this;
    }

    @Override
    public TransitionNode apply(Entity entity) {
        this.action.accept(entity);
        return resultantNode;
    }
}
