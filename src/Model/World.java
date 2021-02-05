package Model;

import Controller.System.ModelSystem;
import Model.Component.Component;
import Model.Component.ComponentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class World {

    ArrayList<Entity> entities;
    ModelSystem modelSystem;

    HashMap<ComponentType, HashMap<Entity, Component>> components = new HashMap<>();

    public World(ModelSystem modelSystem){
        entities = new ArrayList<>();
        this.modelSystem = modelSystem;
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public boolean removeEntity(String entityID){
        return entities.removeIf(e -> e.getID().equals(entityID));
    }

    public Entity findEntityByID(String entityID){
        return entities.stream().filter(e -> e.getID().equals(entityID)).findFirst().orElse(null);
    }

    public List<Entity> findEntitiesByPredicate(Predicate<? super Entity> predicate){
        return entities.stream().filter(predicate).collect(Collectors.toList());
    }

    public void addComponent(Entity entity, Component component){
        if(components.containsKey(component.getComponentType())){
            components.get(component.getComponentType()).put(entity, component);
        } else {
            HashMap<Entity, Component> componentHashMap = new HashMap<>();
            componentHashMap.put(entity, component);
            components.put(component.getComponentType(), componentHashMap);
        }
    }

    public <T extends Component> HashMap<Entity, T> getComponents(ComponentType type){
        if(components.containsKey(type)){
            return (HashMap<Entity, T>)components.get(type);
        } else {
            return new HashMap<>();
        }
    }
}
