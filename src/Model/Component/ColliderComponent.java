package Model.Component;

import Model.Physics.Collider.Collider;
import Model.Physics.CollisionInfo;

import java.util.function.Consumer;

public class ColliderComponent extends Component{

    public ColliderComponent(ComponentType type, Collider collider, Consumer<CollisionInfo> collisionResponse) {
        super(type);

        this.setPrerequisites(ComponentType.POSITION);

        this.setProperty("collider", collider);
        this.setProperty("collisionResponse", collisionResponse);
    }

    public ColliderComponent(Collider collider, Consumer<CollisionInfo> collisionResponse) {
        super(ComponentType.COLLIDER);

        this.setPrerequisites(ComponentType.POSITION);

        this.setProperty("collider", collider);
        this.setProperty("collisionResponse", collisionResponse);
    }
}
