package Model;

import Model.Component.Component;
import Model.Component.ComponentType;

import java.awt.*;
import java.util.HashMap;

public abstract class Entity {

    World world;

    HashMap<ComponentType, Component> components = new HashMap<>();

    String id;

    public Entity(World world, String entityID){
        this.world = world;
        this.id = entityID;
    }

    public String getID(){
        return id;
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof Entity){
            return this.getID().equals(((Entity)obj).getID());
        }
        return false;
    }

    public void addComponent(Component component){

        if(component.hasPrerequisites(this)) {
            components.put(component.getComponentType(), component);
            this.world.addComponent(this, component);
        } else {
            java.lang.System.out.println(component.getComponentType() + " Component was unable to be added to entity: " + getID());
        }
    }

    public < T extends Component > T getComponent(ComponentType type){
        if(components.containsKey(type)){
            return (T)components.get(type);
        } else {
            return null;
        }
    }

    public boolean hasComponent(ComponentType type){
        return components.containsKey(type);
    }

    public boolean hasComponents(ComponentType... types){
        for(ComponentType type : types){
            if(!hasComponent(type)){
                return false;
            }
        }
        return true;
    }

    public boolean removeComponent(ComponentType type){
        return (components.remove(type) != null);
    }
}
