package Model;

import Controller.Model.ModelController;

public abstract class Entity {

    String id;

    public Entity(String entityID){
        this.id = entityID;
    }

    public abstract void update(ModelController modelController);

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
}
