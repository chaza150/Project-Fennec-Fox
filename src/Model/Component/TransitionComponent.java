package Model.Component;

import Model.Entity;
import Model.Transition.TransitionGraph;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class TransitionComponent extends Component{

    public TransitionComponent(ComponentType componentType, TransitionGraph activeTransition){
        super(componentType);

        this.setProperty("activeTransition", activeTransition);
    }
}
