package Controller.System;

import Controller.Interaction.KeyboardInteraction;
import Controller.Settings;
import Controller.SystemManager;

public class InteractionSystem extends System{

    public KeyboardInteraction keyboardInteraction;

    public InteractionSystem(SystemManager sysManager){
        super(sysManager, SystemType.INTERACTION);
        setPrerequisites(SystemType.VIEW);
        setInitPrerequisites(SystemType.VIEW);
        setUpdatePrerequisites(SystemType.VIEW);

        this.keyboardInteraction = new KeyboardInteraction(Settings.TYPE_WINDOW_MILLIS);
    }

    public void init(){}

    @Override
    public void update() {
        keyboardInteraction.update();
    }

}
