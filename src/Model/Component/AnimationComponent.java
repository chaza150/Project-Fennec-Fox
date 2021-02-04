package Model.Component;

import Controller.System.SystemType;
import Model.Transition.Animation;
import Model.Transition.TransitionGraph;

public class AnimationComponent extends TransitionComponent{

    public AnimationComponent(Animation activeAnimation){
        super(ComponentType.ANIMATION, activeAnimation);
    }

}
