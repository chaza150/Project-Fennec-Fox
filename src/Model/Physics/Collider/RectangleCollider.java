package Model.Physics.Collider;

import Model.Component.ComponentType;
import Model.Component.PositionComponent;
import Model.Entity;

public class RectangleCollider extends Collider{

    float width;
    float height;

    public RectangleCollider(Entity parentEntity, float width, float height){
        super(parentEntity, ColliderType.RECTANGLE);
        this.addCollisionEvent(collider -> {
            PositionComponent ownPositionComponent = getEntity().<PositionComponent>getComponent(ComponentType.POSITION);

            float ownX = ownPositionComponent.getProperty("x");
            float ownY = ownPositionComponent.getProperty("y");

            switch(collider.getColliderType()){
                case RECTANGLE:
                    PositionComponent otherPositionComponent = collider.getEntity().<PositionComponent>getComponent(ComponentType.POSITION);
                    float otherX = otherPositionComponent.getProperty("x");
                    float otherY = otherPositionComponent.getProperty("y");

                    RectangleCollider rectangleCollider = (RectangleCollider) collider;
                    float otherWidth = rectangleCollider.width;
                    float otherHeight = rectangleCollider.height;

                    return(ownX <= otherX + otherWidth && ownX + width >= otherX && ownY <= otherY + otherHeight && ownY + height >= otherY);

                default:
                    return false;
            }

        });
        this.width = width;
        this.height = height;
    }
}
