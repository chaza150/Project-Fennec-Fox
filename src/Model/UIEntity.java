package Model;

import Model.Component.*;
import Model.Component.Component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class UIEntity extends Entity {

    int width = 0;
    int height = 0;

    boolean green = true;

    public UIEntity(World world, String entityID, int x, int y, int width, int height) {
        super(world, entityID);
        this.width = width;
        this.height = height;

        this.addComponent(new PositionComponent(20, 20));

        this.addComponent(new GraphicsComponent(0, getPainter(Color.GREEN)));

        this.green = true;

        this.addComponent(new MouseEventComponent(mouseInter -> {
            for(MouseEvent mouseEvent : mouseInter.getMouseClicks()) {
                if (mouseEvent.getX() >= x && mouseEvent.getX() <= x + width && mouseEvent.getY() >= y && mouseEvent.getY() <= y + height) {
                    if (green) {
                        getComponent(ComponentType.GRAPHICS).setProperty("painter", getPainter(Color.BLUE));
                    } else {
                        getComponent(ComponentType.GRAPHICS).setProperty("painter", getPainter(Color.GREEN));
                    }
                    green = !green;
                }
            }

        }));
    }

    public Consumer<Graphics> getPainter(Color color){
        return graphics -> {
            graphics.setColor(color);
            PositionComponent position = getComponent(ComponentType.POSITION);
            graphics.fillRect(position.<Float>getProperty("x").intValue(), position.<Float>getProperty("y").intValue(), width, height);
        };
    }
}
