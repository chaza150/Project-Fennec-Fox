package Controller.System;

import Controller.SystemManager;
import Model.Component.Component;
import Model.Component.ComponentType;
import Model.Entity;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class VelocitySystem extends System{

    long lastUpdateTime;
    int updateTime;

    public VelocitySystem(SystemManager sysManager, int updateTime) {
        super(sysManager, SystemType.VELOCITY);

        setPrerequisites(SystemType.MODEL);
        setInitPrerequisites(SystemType.MODEL);
        setUpdatePrerequisites(SystemType.MODEL);

        this.updateTime = updateTime;
    }

    @Override
    public void update() {
        long currentTime = java.lang.System.currentTimeMillis();
        int deltaTime = (int)(currentTime - lastUpdateTime);
        if(deltaTime > updateTime) {
            lastUpdateTime = currentTime;
            HashMap<Entity, Component> velocityComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.VELOCITY);
            for (Entity entity : velocityComponents.keySet()) {
                entity.getComponent(ComponentType.POSITION).apply("x", e -> (float) e + velocityComponents.get(entity).<Float>getProperty("x") * ((float)(deltaTime)/1000f));
                entity.getComponent(ComponentType.POSITION).apply("y", e -> (float) e + velocityComponents.get(entity).<Float>getProperty("y") * ((float)(deltaTime)/1000f));
            }
        }
    }

    @Override
    public void init() {
        lastUpdateTime = java.lang.System.currentTimeMillis();
    }
}
