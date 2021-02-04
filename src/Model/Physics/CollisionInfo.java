package Model.Physics;

import Model.Physics.Collider.Collider;

public class CollisionInfo {

    Collider collider1;
    Collider collider2;

    public CollisionInfo(Collider collider1, Collider collider2){
        this.collider1 = collider1;
        this.collider2 = collider2;
    }
}
