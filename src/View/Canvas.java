package View;

import Controller.GraphicsController;
import Controller.ViewController;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    ViewController viewController;
    GraphicsController graphicsController;

    public Canvas(ViewController viewController){
        this.viewController = viewController;
        this.setBackground(Color.BLUE);
    }

    public void setGraphicsController(GraphicsController graphicsController) {
        this.graphicsController = graphicsController;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        graphicsController.paint(g);
    }
}
