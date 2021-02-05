package Controller.System;

import Controller.SystemManager;

import java.util.Arrays;
import java.util.Collection;

public abstract class System {

    //TODO: Add Pre-Requisite Systems

    SystemManager sysManager;
    SystemType type;

    //What other systems need to exist
    SystemType[] prerequisites = {};
    //What other systems need to have already been initialised
    SystemType[] initPrerequisites = {};
    //What other systems need to already have been updated
    SystemType[] updatePrerequisites = {};

    public System(SystemManager sysManager, SystemType type){
        this.type = type;
        this.sysManager = sysManager;
    }

    public void setPrerequisites(SystemType... prerequisites){
        this.prerequisites = prerequisites;
    }

    public void setInitPrerequisites(SystemType... prerequisites){
        this.initPrerequisites = prerequisites;
    }

    public void setUpdatePrerequisites(SystemType... prerequisites){
        this.updatePrerequisites = prerequisites;
    }

    public boolean hasInitPrerequisites(Collection<SystemType> types){
        return !Arrays.stream(initPrerequisites).anyMatch(t -> !types.contains(t));
    }

    public boolean hasUpdatePrerequisites(Collection<SystemType> types){
        return !Arrays.stream(updatePrerequisites).anyMatch(t -> !types.contains(t));
    }

    public boolean hasPrerequisites(){
        return !Arrays.stream(prerequisites).anyMatch(t -> !sysManager.containsSystem(t));
    }

    public abstract void update();

    public abstract void init();

    public SystemType getSystemType(){
        return type;
    }
}
