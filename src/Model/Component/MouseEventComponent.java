package Model.Component;

import Controller.Interaction.MouseInteraction;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MouseEventComponent extends Component {


    public MouseEventComponent(Consumer<MouseInteraction> mouseResponse) {
        super(ComponentType.MOUSE_EVENT);

        this.setProperty("mouseResponse", mouseResponse);
    }
}
