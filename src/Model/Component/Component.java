package Model.Component;

import Controller.System.SystemType;
import Model.Entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public abstract class Component {

    ComponentType[] prerequisites = {};

    HashMap<String, Object> properties = new HashMap<>();
    ComponentType type = ComponentType.NONE;

    public Component(ComponentType type){
        this.type = type;
    }

    public <T extends Object> T getProperty(String propertyName){
        if(properties.containsKey(propertyName)){
            return (T)(properties.get(propertyName));
        } else {
            return null;
        }
    }

    public void setProperty(String propertyName, Object value){
        properties.put(propertyName, value);
    }

    public ComponentType getComponentType(){
        return type;
    }

    public void apply(String propertyName, Function<Object, Object> function){
        setProperty(propertyName, function.apply(getProperty(propertyName)));
    }

    public void setPrerequisites(ComponentType... prerequisites){
        this.prerequisites = prerequisites;
    }

    public boolean hasPrerequisites(Entity entity){
        return !Arrays.stream(prerequisites).anyMatch(t -> !entity.hasComponent(t));
    }



}
