package Model.Transition;

import Model.Entity;

import java.util.function.Predicate;

public class SplitGate extends GateNode{

    Predicate<Entity> predicate;
    TransitionNode trueNode;
    TransitionNode falseNode;

    public SplitGate(Predicate<Entity> predicate, TransitionNode trueNode, TransitionNode falseNode){
        this.predicate = predicate;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    public SplitGate(Predicate<Entity> predicate){
        this.predicate = predicate;
    }

    public void addConditionalNodes(TransitionNode trueNode, TransitionNode falseNode){
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    @Override
    public TransitionNode apply(Entity entity) {
        if(predicate.test(entity)){
            return trueNode;
        } else {
            return falseNode;
        }
    }

}
