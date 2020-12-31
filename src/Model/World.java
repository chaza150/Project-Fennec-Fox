package Model;

import Controller.Model.ModelController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class World {

    ArrayList<Entity> entities;
    ModelController modelController;

    public World(ModelController modelController){
        entities = new ArrayList<>();
        this.modelController = modelController;
    }

    public void update(){
        for(Entity entity : entities){
            entity.update(this.modelController);
        }
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
}
