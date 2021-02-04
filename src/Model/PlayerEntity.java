package Model;

import Model.Component.*;
import Model.Component.Component;
import Model.Transition.Animation;

import java.awt.*;
import java.util.function.Consumer;

public class PlayerEntity extends Entity{

    float acceleration = 0.1f;

    public PlayerEntity(World world, String playerID, int x, int y){
        super(world, playerID);
        addComponent(new PositionComponent(x,y));
        addComponent(new VelocityComponent(0,0));

        addComponent(new GraphicsComponent(0, g -> {
            g.setColor(Color.RED);
            Component position = getComponent(ComponentType.POSITION);
            g.fillOval(position.<Float>getProperty("x").intValue(), position.<Float>getProperty("y").intValue(), 20, 20);
        }));

        addComponent(new PlayerControlComponent());
        addComponent(new TimingComponent());

        addComponent(new AnimationComponent(new Animation()
                .addFrame(1000, e -> e.getComponent(ComponentType.VELOCITY).apply("x", velX -> 200f))
                .addFrame(1000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.MAGENTA)))
                .addFrame(2000, e -> e.getComponent(ComponentType.VELOCITY).apply("x", velX -> -200f))
                .addFrame(2000, e -> e.getComponent(ComponentType.GRAPHICS).apply("painter", p -> getPainter(Color.CYAN)))
                .setLooping(true)));
    }

    public Consumer<Graphics> getPainter(Color color){
        return g -> {
            g.setColor(color);
            Component position = getComponent(ComponentType.POSITION);
            g.fillOval(position.<Float>getProperty("x").intValue(), position.<Float>getProperty("y").intValue(), 20, 20);
        };
    }
}
