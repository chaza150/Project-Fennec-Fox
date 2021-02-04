package Controller.System;

import Controller.Interaction.KeyboardInteraction;
import Controller.SystemManager;
import Model.Component.Component;
import Model.Component.ComponentType;
import Model.Entity;

import java.util.HashMap;

public class PlayerControlSystem extends System{

    long lastUpdateTime;
    int updateTime;

    public PlayerControlSystem(SystemManager sysManager, int updateTime) {
        super(sysManager, SystemType.PLAYER_CONTROL);

        setPrerequisites(SystemType.VELOCITY);
        setInitPrerequisites(SystemType.VELOCITY);
        setUpdatePrerequisites(SystemType.VELOCITY);

        this.updateTime = updateTime;
    }

    @Override
    public void update() {
        long currentTime = java.lang.System.currentTimeMillis();
        if((currentTime - lastUpdateTime) > updateTime) {
            HashMap<Entity, Component> velocityComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.VELOCITY);
            for (Entity entity : velocityComponents.keySet()) {

                KeyboardInteraction keyboardInteraction = sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).keyboardInteraction;

                Component velocityComponent = velocityComponents.get(entity);

                float acceleration = 0.04f;

                if (keyboardInteraction.isKeyDown('w')) {
                    velocityComponent.apply("y", e -> (float) e - acceleration);
                }
                if (keyboardInteraction.isKeyDown('s')) {
                    velocityComponent.apply("y", e -> (float) e + acceleration);
                }
                if (keyboardInteraction.isKeyDown('a')) {
                    velocityComponent.apply("x", e -> (float) e - acceleration);
                }
                if (keyboardInteraction.isKeyDown('d')) {
                    velocityComponent.apply("x", e -> (float) e + acceleration);
                }
            }
        }
    }

    @Override
    public void init() {}
}
