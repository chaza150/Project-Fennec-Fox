package Model.Transition;

import Model.Component.ComponentType;
import Model.Entity;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Animation extends TransitionGraph {

    ArrayList<TransitionNode> nodes = new ArrayList<>();
    ArrayList<Integer> keyframeTimes = new ArrayList<>();

    int animationRunTime = 0;
    boolean looping = false;

    public Animation() {
        super(new PassageAction(e -> {}));
        nodes.add(rootNode);
        keyframeTimes.add(0);
    }

    public Animation addFrame(int keyframeTime, Consumer<Entity> frameAction){
        if(keyframeTime < 0){
            return this;
        }

        for(int i = 1; i < keyframeTimes.size(); i++){
            if(keyframeTime < keyframeTimes.get(i)){
                int nodeIndex = (i*2)-1;
                PassageAction frameNode = new PassageAction(frameAction, nodes.get(nodeIndex));
                PredicateGate timeCheck = new PredicateGate(e -> animationRunTime >= keyframeTime, frameNode);
                ((PassageAction)(nodes.get(nodeIndex-1))).addResultantNode(timeCheck);
                nodes.add(nodeIndex, timeCheck);
                nodes.add(nodeIndex + 1, frameNode);
                keyframeTimes.add(i, keyframeTime);
                return this;
            }
        }

        PassageAction frameNode = new PassageAction(frameAction);
        PredicateGate timeCheck = new PredicateGate(e -> animationRunTime >= keyframeTime, frameNode);
        ((PassageAction)(nodes.get(nodes.size()-1))).addResultantNode(timeCheck);
        nodes.add(timeCheck);
        nodes.add(frameNode);
        keyframeTimes.add(keyframeTime);

        setLooping(looping);

        return this;
    }

    @Override
    public TransitionNode apply(Entity entity){
        this.animationRunTime += entity.getComponent(ComponentType.TIMING).<Integer>getProperty("deltaTime");
        return super.apply(entity);
    }

    public Animation setLooping(boolean looping){
        this.looping = looping;
        if(looping){
            PassageAction timeResetAction = new PassageAction(e -> this.animationRunTime = 0, this.rootNode);
            ((PassageAction)nodes.get(nodes.size()-1)).addResultantNode(timeResetAction);
        } else {
            ((PassageAction)nodes.get(nodes.size()-1)).addResultantNode(null);
        }

        return this;
    }
}
