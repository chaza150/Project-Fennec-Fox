package Model.Transition;

import Model.Entity;

import java.util.ArrayList;
import java.util.function.Predicate;

public class SwitchGate extends GateNode {

    ArrayList<Predicate<Entity>> predicates;
    ArrayList<TransitionNode> transitions;

    public SwitchGate(ArrayList<Predicate<Entity>> predicates, ArrayList<TransitionNode> transitions){
        this.predicates = predicates;
        this.transitions = transitions;
    }

    public SwitchGate(){}

    public SwitchGate addSwitchCase(Predicate<Entity> predicate, TransitionNode resultantNode){
        predicates.add(predicate);
        transitions.add(resultantNode);
        return this;
    }

    @Override
    public TransitionNode apply(Entity entity) {
        for(int predicateIndex = 0; predicateIndex < predicates.size(); predicateIndex++){
            if(predicates.get(predicateIndex).test(entity)){
                return transitions.get(predicateIndex);
            }
        }
        return this;
    }
}
