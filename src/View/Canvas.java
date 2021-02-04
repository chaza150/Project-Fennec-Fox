package View;

import Controller.System.GraphicsSystem;
import Controller.System.ViewSystem;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    ViewSystem viewSystem;
    GraphicsSystem graphicsSystem;

    public Canvas(ViewSystem viewSystem){
        this.viewSystem = viewSystem;
        this.setBackground(Color.BLUE);
    }

    public void setGraphicsSystem(GraphicsSystem graphicsSystem) {
        this.graphicsSystem = graphicsSystem;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        graphicsSystem.paint(g);
    }
}
