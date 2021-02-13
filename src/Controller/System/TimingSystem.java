package Controller.System;

import Controller.SystemManager;
import Model.Component.Component;
import Model.Component.ComponentType;
import Model.Entity;

import java.util.HashMap;
import java.util.Map;

public class TimingSystem extends System{

    long prevUpdateTime;
    int deltaTime = 0;

    public TimingSystem(SystemManager sysManager) {
        super(sysManager, SystemType.TIMING);
    }

    @Override
    public void update() {
        long currentTime = java.lang.System.currentTimeMillis();
        deltaTime = (int)(currentTime - prevUpdateTime);
        prevUpdateTime = currentTime;

        Map<Entity, Component> timingComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.TIMING);
        if(timingComponents.size() > 0) {
            timingComponents.values().stream().forEach(e -> e.setProperty("deltaTime", deltaTime));
        }
    }

    @Override
    public void init() {
        prevUpdateTime = java.lang.System.currentTimeMillis();
    }
}
