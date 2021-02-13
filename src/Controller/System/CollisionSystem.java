package Controller.System;

import Controller.SystemManager;
import Model.Component.ColliderComponent;
import Model.Component.ComponentType;
import Model.Entity;
import Model.Physics.Bounding.BoundingVolume;
import Model.Physics.Collider.Collider;
import Model.Physics.CollisionInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CollisionSystem extends System{
    public CollisionSystem(SystemManager sysManager) {
        super(sysManager, SystemType.COLLISION);

        setPrerequisites(SystemType.MODEL);
        setUpdatePrerequisites(SystemType.VELOCITY);
    }

    @Override
    public void update() {
        Map<Entity, ColliderComponent> colliderComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.COLLIDER);
        if(colliderComponents.size() > 1) {
            Set<Collider> colliders = colliderComponents.values().stream().map(colliderComp -> colliderComp.<Collider>getProperty("collider")).collect(Collectors.toSet());

            //vvTO BE REMOVED OR MADE OPTIONALvv
//            Set<Collider> collidersToCheck = new HashSet<>();
//            collidersToCheck.addAll(colliders);
//
//            int collisionsCheckedOld = 0;
//            for (Collider collider : colliders) {
//                for (Collider otherCollider : collidersToCheck) {
//                    collisionsCheckedOld++;
//                    if (collider.collidesWith(otherCollider) && otherCollider != collider) {
//                    }
//                }
//                collidersToCheck.remove(collider);
//            }
//
//            java.lang.System.out.println("Collisions Checked (OLD): " + collisionsCheckedOld);
            //^^TO BE REMOVED OR MADE OPTIONAL^^

            BoundingVolume bv = new BoundingVolume(colliders, 2);
            Set<CollisionInfo> potentialCollisions = bv.getPotentialCollisions();

            Set<CollisionInfo> collisions = new HashSet<>();

            for(CollisionInfo potentialCollision : potentialCollisions){
                if(potentialCollision.getCollider1().collidesWith(potentialCollision.getCollider2())){
                    collisions.add(potentialCollision);
                }
            }

            for (CollisionInfo collisionInfo : collisions) {
                //java.lang.System.out.println("Collision Between: " + collisionInfo.getCollider1().getEntity().getID() + " AND " + collisionInfo.getCollider2().getEntity().getID());
                colliderComponents.get(collisionInfo.getCollider1().getEntity()).<Consumer<CollisionInfo>>getProperty("collisionResponse").accept(collisionInfo);
                colliderComponents.get(collisionInfo.getCollider2().getEntity()).<Consumer<CollisionInfo>>getProperty("collisionResponse").accept(collisionInfo);
            }
        }
    }

    @Override
    public void init() {

    }
}
