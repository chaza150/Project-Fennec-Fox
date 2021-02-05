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

        setPrerequisites(SystemType.VELOCITY, SystemType.INTERACTION, SystemType.TIMING);
        setUpdatePrerequisites(SystemType.INTERACTION, SystemType.TIMING);

        this.updateTime = updateTime;
    }

    @Override
    public void update() {
        long currentTime = java.lang.System.currentTimeMillis();
        if((currentTime - lastUpdateTime) > updateTime) {
            HashMap<Entity, Component> playerControlComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.PLAYER_CONTROL);
            for (Entity entity : playerControlComponents.keySet()) {

                KeyboardInteraction keyboardInteraction = sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).keyboardInteraction;

                Component velocityComponent = entity.getComponent(ComponentType.VELOCITY);

                float acceleration = 0.2f;
                int deltaTime = entity.getComponent(ComponentType.TIMING).getProperty("deltaTime");

                if (keyboardInteraction.isKeyDown('w')) {
                    velocityComponent.apply("y", e -> (float) e - acceleration * deltaTime);
                }
                if (keyboardInteraction.isKeyDown('s')) {
                    velocityComponent.apply("y", e -> (float) e + acceleration * deltaTime);
                }
                if (keyboardInteraction.isKeyDown('a')) {
                    velocityComponent.apply("x", e -> (float) e - acceleration * deltaTime);
                }
                if (keyboardInteraction.isKeyDown('d')) {
                    velocityComponent.apply("x", e -> (float) e + acceleration * deltaTime);
                }
            }
        }
    }

    @Override
    public void init() {}
}
