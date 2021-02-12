package Model;

import Model.Component.*;
import Model.Component.Component;
import Model.Physics.Collider.CircleCollider;
import Model.Physics.Collider.RectangleCollider;
import Model.Transition.Animation;

import java.awt.*;
import java.util.function.Consumer;

public class PlayerEntity extends Entity{

    float acceleration = 0.1f;
    int radius = 0;

    public PlayerEntity(World world, String entityID, int x, int y, int number, int radius){
        super(world, entityID);
        this.radius = radius;
        addComponent(new PositionComponent(x,y));
        addComponent(new VelocityComponent(0,0));

        addComponent(new GraphicsComponent(0, getPainter(Color.GREEN)));

        if(number == 1) {
            addComponent(new PlayerControlComponent());
        }
        addComponent(new TimingComponent());

        /*if(number == 1) {
            addComponent(new AnimationComponent(new Animation()
                    .addFrame(1000, e -> e.getComponent(ComponentType.VELOCITY).apply("x", velX -> 200f))
                    .addFrame(1000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.MAGENTA)))
                    .addFrame(2000, e -> e.getComponent(ComponentType.VELOCITY).apply("x", velX -> -200f))
                    .addFrame(2000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.CYAN)))
                    .setLooping(true)));
        } else {
            addComponent(new AnimationComponent(new Animation()
                    .addFrame(1000, e -> e.getComponent(ComponentType.VELOCITY).apply("y", velY -> 200f))
                    .addFrame(1000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.BLUE)))
                    .addFrame(2000, e -> e.getComponent(ComponentType.VELOCITY).apply("y", velY -> -200f))
                    .addFrame(2000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.GREEN)))
                    .setLooping(true)));
        }*/

        addComponent(new ColliderComponent(new CircleCollider(this, radius), collisionInfo -> {
            this.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.RED));
            if(!this.hasComponent(ComponentType.PLAYER_CONTROL)){
                this.removeComponent(ComponentType.COLLIDER);
            }
            //this.getComponent(ComponentType.VELOCITY).apply("x", velX -> 0f);
            //this.getComponent(ComponentType.VELOCITY).apply("y", velY -> 0f);
        }));
    }

    public Consumer<Graphics> getPainter(Color color){
        return g -> {
            g.setColor(color);
            Component position = getComponent(ComponentType.POSITION);
            g.fillOval(position.<Float>getProperty("x").intValue()-this.radius, position.<Float>getProperty("y").intValue()-this.radius, radius*2, radius*2);
        };
    }
}
