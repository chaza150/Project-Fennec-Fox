package Model.Physics.Collider;

import Model.Entity;

import java.util.function.Predicate;

public class Collider {

    Predicate<Collider> collisionCheck;
    ColliderType colliderType;
    Entity parentEntity;

    public Collider(Entity parentEntity, ColliderType colliderType){
        this.parentEntity = parentEntity;
        this.colliderType = colliderType;
    }

    public void addCollisionEvent(Predicate<Collider> collisionCheck){
        this.collisionCheck = collisionCheck;
    }

    public Collider(Entity parentEntity, Predicate<Collider> collisionCheck, ColliderType colliderType){
        this.parentEntity = parentEntity;
        this.collisionCheck = collisionCheck;
        this.colliderType = colliderType;
    }

    public boolean collidesWith(Collider otherCollider){
        return collisionCheck.test(otherCollider);
    }

    public ColliderType getColliderType(){
        return colliderType;
    }

    public Entity getEntity(){
        return parentEntity;
    }
}
