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

    public PlayerEntity(World world, String playerID, int x, int y, int number){
        super(world, playerID);
        addComponent(new PositionComponent(x,y));
        addComponent(new VelocityComponent(0,0));

        addComponent(new GraphicsComponent(0, getPainter(Color.GREEN)));

        addComponent(new PlayerControlComponent());
        addComponent(new TimingComponent());

        if(number == 1) {
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
        }

        addComponent(new ColliderComponent(new CircleCollider(this, 50), collisionInfo -> {
            this.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.RED));
            //this.getComponent(ComponentType.VELOCITY).apply("x", velX -> 0f);
            //this.getComponent(ComponentType.VELOCITY).apply("y", velY -> 0f);
        }));
    }

    public Consumer<Graphics> getPainter(Color color){
        return g -> {
            g.setColor(color);
            Component position = getComponent(ComponentType.POSITION);
            g.fillOval(position.<Float>getProperty("x").intValue()-50, position.<Float>getProperty("y").intValue()-50, 100, 100);
        };
    }
}
