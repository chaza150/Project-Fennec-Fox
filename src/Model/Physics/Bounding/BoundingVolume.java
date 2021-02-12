package Model.Physics.Bounding;

import Model.Physics.Collider.Collider;
import Model.Physics.CollisionInfo;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoundingVolume {

    Set<BoundingVolume> childrenVolumes = new HashSet<>();
    Set<Collider> leafColliders = new HashSet<>();

    int left;
    int right;
    int top;
    int bottom;

    public BoundingVolume(Collection<Collider> colliders, int volumeCapacity){

        if(colliders.size() == 0){
            this.left = 0;
            this.right = 0;
            this.top = 0;
            this.bottom = 0;
            return;
        }

        List<Rectangle> boundingRects = colliders.stream().map(c -> c.getBoundingRectangle()).collect(Collectors.toList());
        Stream<Integer> lefts = boundingRects.stream().map(c -> c.x);
        Stream<Integer> rights = boundingRects.stream().map(c -> c.x + c.width);
        Stream<Integer> tops = boundingRects.stream().map(c -> c.y);
        Stream<Integer> bottoms = boundingRects.stream().map(c -> c.y + c.height);

        this.left = lefts.min(Integer::compare).get();
        this.right = rights.max(Integer::compare).get();
        this.top = tops.min(Integer::compare).get();
        this.bottom = bottoms.max(Integer::compare).get();

        if(colliders.size() > volumeCapacity){

            int xrange = this.right - this.left;
            int yrange = this.bottom - this.top;

            if(xrange >= yrange){
                List<Collider> xSortedColliders = colliders.stream().sorted(Comparator.comparing(c -> c.getBoundingRectangle().x)).collect(Collectors.toList());
                childrenVolumes.add(new BoundingVolume(xSortedColliders.subList(0, colliders.size()/2), volumeCapacity));
                childrenVolumes.add(new BoundingVolume(xSortedColliders.subList(colliders.size()/2, colliders.size()), volumeCapacity));
            } else {
                List<Collider> ySortedColliders = colliders.stream().sorted(Comparator.comparing(c -> c.getBoundingRectangle().y)).collect(Collectors.toList());
                childrenVolumes.add(new BoundingVolume(ySortedColliders.subList(0, colliders.size()/2), volumeCapacity));
                childrenVolumes.add(new BoundingVolume(ySortedColliders.subList(colliders.size()/2, colliders.size()), volumeCapacity));
            }

        } else {
            leafColliders.addAll(colliders);
        }
    }

    public Set<CollisionInfo> getPotentialCollisions(){
        Set<BoundingVolume> otherChildren = new HashSet<>();
        otherChildren.addAll(childrenVolumes);

        Set<CollisionInfo> potentialCollisions = new HashSet<>();

        for(BoundingVolume child1 : childrenVolumes){
            for(BoundingVolume child2 : otherChildren){
                if(child1.collidesWith(child2) && child1 != child2){
                    Set<Collider> leftColliders = child1.getPotentialCollidingColliders(child2);
                    Set<Collider> rightColliders = child2.getPotentialCollidingColliders(child1);

                    for(Collider c1 : leftColliders){
                        for(Collider c2 : rightColliders){
                            potentialCollisions.add(new CollisionInfo(c1, c2));
                        }
                    }
                }
            }

            potentialCollisions.addAll(child1.getPotentialCollisions());

            otherChildren.remove(child1);
        }

        Set<Collider> otherLeaf = new HashSet<>();
        otherLeaf.addAll(leafColliders);

        for(Collider c1 : leafColliders){
            for(Collider c2 : otherLeaf){
                if(c1 != c2){
                    potentialCollisions.add(new CollisionInfo(c1, c2));
                }
            }
        }

        return potentialCollisions;
    }

    public Set<Collider> getPotentialCollidingColliders(BoundingVolume b2){
        if(this.collidesWith(b2)){
            Set<Collider> colliderSet = new HashSet<>();
            colliderSet.addAll(this.leafColliders);
            for(BoundingVolume child : this.childrenVolumes){
                colliderSet.addAll(child.getPotentialCollidingColliders(b2));
            }
            return colliderSet;
        } else {
//            Set<Collider> colliderSet = new HashSet<>();
//            for(BoundingVolume child : this.childrenVolumes){
//                colliderSet.addAll(child.getPotentialCollidingColliders(b2));
//            }
//            if(colliderSet.size() > 0){
//                System.out.println("VIOLATION!");
//            }
            return new HashSet<>();
        }
    }

    public boolean collidesWith(BoundingVolume b){
        return (this.top <= b.bottom && this.bottom >= b.top && this.right >= b.left && this.left <= b.right);

    }
}
