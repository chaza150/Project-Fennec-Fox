package Controller.System;

import Controller.Interaction.KeyboardInteraction;
import Controller.Interaction.MouseInteraction;
import Controller.Settings;
import Controller.SystemManager;

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
        keyboardInteraction.update();
        mouseInteraction.update();
    }

}
