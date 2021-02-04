package Controller;

import Controller.System.*;
import Controller.System.System;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SystemManager {

    HashMap<SystemType, System> systems = new HashMap<>();

    public SystemManager(){
        addSystem(new ModelSystem(this));
        addSystem(new ViewSystem(this));
        addSystem(new GraphicsSystem(this));
        addSystem(new InteractionSystem(this));
        addSystem(new VelocitySystem(this, Settings.PHYSICS_UPDATE_TIME));
        addSystem(new PlayerControlSystem(this, Settings.PHYSICS_UPDATE_TIME));
        addSystem(new TimingSystem(this));
        addSystem(new AnimationSystem(this));
    }

    public void init(){
        HashSet<SystemType> initTypes = new HashSet<>();
        Set<SystemType> sysToInit = systems.keySet();
        while(!sysToInit.isEmpty()) {
            HashSet<SystemType> sysToInitNew = new HashSet<>();
            for (SystemType sysType : sysToInit) {
                System system = systems.get(sysType);
                if (system.hasInitPrerequisites(initTypes)) {
                    system.init();
                    initTypes.add(sysType);
                } else {
                    sysToInitNew.add(sysType);
                }
            }
            sysToInit = sysToInitNew;
        }
    }

    public void update(){
        HashSet<SystemType> updateTypes = new HashSet<>();
        Set<SystemType> sysToUpdate = systems.keySet();
        while(!sysToUpdate.isEmpty()) {
            HashSet<SystemType> sysToUpdateNew = new HashSet<>();
            for (SystemType sysType : sysToUpdate) {
                System system = systems.get(sysType);
                if (system.hasUpdatePrerequisites(updateTypes)) {
                    system.update();
                    updateTypes.add(sysType);
                } else {
                    sysToUpdateNew.add(sysType);
                }
            }
            sysToUpdate = sysToUpdateNew;
        }
    }

    public void addSystem(System system){
        if(system.hasPrerequisites()) {
            systems.put(system.getSystemType(), system);
        } else {
            java.lang.System.out.println(system.getSystemType() + " System was unable to be added.");
        }
    }

    public <T extends System> T getSystem(SystemType type){
        if(systems.containsKey(type)){
            return (T)systems.get(type);
        } else {
            return null;
        }
    }

    public boolean containsSystem(SystemType type){
        return systems.containsKey(type);
    }
}
