package View.Graphics;

import Controller.GraphicsController;

import java.awt.*;

public class Painter {

    GraphicsController graphicsController;

    public Painter(GraphicsController graphicsController){
        this.graphicsController = graphicsController;
    }

    //TODO: May require passing of model
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Painter not painting anything", 20, 20);
    }
}
