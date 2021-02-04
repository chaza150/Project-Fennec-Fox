package Controller.System;

import Controller.SystemManager;
import Model.Component.Component;
import Model.Component.ComponentType;
import Model.Entity;
import Model.Transition.TransitionGraph;

import java.util.HashMap;

public class AnimationSystem extends System {

    public AnimationSystem(SystemManager sysManager) {
        super(sysManager, SystemType.ANIMATION);

        setPrerequisites(SystemType.GRAPHICS, SystemType.TIMING);
    }

    @Override
    public void update() {
        HashMap<Entity, Component> animationComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.ANIMATION);
        for (Entity entity : animationComponents.keySet()) {
            animationComponents.get(entity).<TransitionGraph>getProperty("activeTransition").apply(entity);
        }
    }

    @Override
    public void init() {}
}
