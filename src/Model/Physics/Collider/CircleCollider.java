package Model.Physics.Collider;

import Model.Component.ComponentType;
import Model.Component.PositionComponent;
import Model.Entity;

import java.awt.*;

public class CircleCollider extends Collider{

    float radius;

    public CircleCollider(Entity parentEntity, float radius){
        super(parentEntity, ColliderType.CIRCLE);
        this.addCollisionEvent(collider -> {
            PositionComponent ownPositionComponent = getEntity().<PositionComponent>getComponent(ComponentType.POSITION);

            float ownX = ownPositionComponent.getProperty("x");
            float ownY = ownPositionComponent.getProperty("y");

            switch(collider.getColliderType()){
                case CIRCLE:
                    PositionComponent otherPositionComponent = collider.getEntity().<PositionComponent>getComponent(ComponentType.POSITION);
                    float otherX = otherPositionComponent.getProperty("x");
                    float otherY = otherPositionComponent.getProperty("y");

                    CircleCollider circleCollider = (CircleCollider) collider;
                    float otherRadius = circleCollider.radius;

                    float deltaX = ownX - otherX;
                    float deltaY = ownY - otherY;
                    float radDistance = radius + otherRadius;

                    return(deltaX*deltaX + deltaY*deltaY <= radDistance*radDistance);

                default:
                    return false;
            }

        });
        this.radius = radius;
    }

    @Override
    public Rectangle getBoundingRectangle(){
        PositionComponent positionComponent = getEntity().getComponent(ComponentType.POSITION);
        return new Rectangle((int)(positionComponent.<Float>getProperty("x")-radius-1), (int)(positionComponent.<Float>getProperty("y")-radius-1), (int)(radius*2), (int)(radius*2));
    }

}
