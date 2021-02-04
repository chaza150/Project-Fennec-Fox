package Controller.System;

import Controller.SystemManager;
import Model.Component.Component;
import Model.Component.ComponentType;
import Model.Entity;

import java.util.HashMap;

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

        HashMap<Entity, Component> timingComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.TIMING);
        timingComponents.values().stream().forEach(e -> e.setProperty("deltaTime", deltaTime));
    }

    @Override
    public void init() {
        prevUpdateTime = java.lang.System.currentTimeMillis();
    }
}
