package Controller.System;

import Controller.Interaction.KeyboardInteraction;
import Controller.Interaction.MouseInteraction;
import Controller.Settings;
import Controller.SystemManager;
import Model.Component.ComponentType;
import Model.Component.MouseEventComponent;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

public class InteractionSystem extends System{

    public KeyboardInteraction keyboardInteraction;
    public MouseInteraction mouseInteraction;

    public InteractionSystem(SystemManager sysManager){
        super(sysManager, SystemType.INTERACTION);
        setPrerequisites(SystemType.VIEW);
        setInitPrerequisites(SystemType.VIEW);
        setUpdatePrerequisites(SystemType.VIEW);

        this.keyboardInteraction = new KeyboardInteraction(Settings.TYPE_WINDOW_MILLIS);
        this.mouseInteraction = new MouseInteraction();
    }

    public void init(){}

    @Override
    public void update() {

        Collection<MouseEventComponent> mouseEventComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.<MouseEventComponent>getComponents(ComponentType.MOUSE_EVENT).values();

        for(MouseEventComponent mouseComponent : mouseEventComponents){
            mouseComponent.<Consumer<MouseInteraction>>getProperty("mouseResponse").accept(mouseInteraction);
        }

        keyboardInteraction.update();
        mouseInteraction.update();
    }

}
