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

        this.addComponent(new PositionComponent(x, y));

        this.addComponent(new GraphicsComponent(0, getPainter(Color.GREEN)));


        this.addComponent(new MouseEventComponent(mouseInter -> {
            Point mouseCoords = mouseInter.getCurrentMouseCoords();
            if(mouseInter.isMouseButtonDown(MouseEvent.BUTTON1) && (mouseCoords.getX() >= x && mouseCoords.getX() <= x + width && mouseCoords.getY() >= y && mouseCoords.getY() <= y + height)){
                this.getComponent(ComponentType.GRAPHICS).setProperty("painter", getPainter(Color.GREEN));
            } else {
                this.getComponent(ComponentType.GRAPHICS).setProperty("painter", getPainter(Color.BLUE));
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
