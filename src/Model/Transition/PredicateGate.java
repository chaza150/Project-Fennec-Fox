package Model.Transition;

import Model.Entity;

import java.util.function.Predicate;

public class PredicateGate extends GateNode{

    Predicate<Entity> predicate;
    TransitionNode conditionalNode = null;

    public PredicateGate(Predicate<Entity> predicate, TransitionNode conditionalNode){
        this.predicate = predicate;
        this.conditionalNode = conditionalNode;
    }

    public PredicateGate(Predicate<Entity> predicate){
        this.predicate = predicate;
    }

    public void addConditionalNode(TransitionNode conditionalNode){
        this.conditionalNode = conditionalNode;
    }

    @Override
    public TransitionNode apply(Entity entity) {
        if(predicate.test(entity)){
            return conditionalNode;
        } else {
            return this;
        }
    }
}
