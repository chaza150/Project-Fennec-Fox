package Controller;

import View.Canvas;
import View.Graphics.Painter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GraphicsController extends Controller{

    ViewController viewController;
    Canvas canvas;

    public Painter painter;

    ArrayList<BufferedImage> buffers;

    boolean ready = false;

    public GraphicsController(ViewController viewController){
        this.viewController = viewController;
        this.painter = new Painter(this);
        this.canvas = viewController.canvas;
        this.buffers = new ArrayList<>();
    }

    @Override
    public void init() {
        for(int i = 0; i < Settings.BUFFER_COUNT; i++){
            this.buffers.add(new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_RGB));
        }
        this.canvas.setGraphicsController(this);
        ready = true;
    }

    @Override
    public void update() {
        if(buffers.size() > 1) {
            painter.paint(this.buffers.get(1).getGraphics());
        } else {
            painter.paint(this.buffers.get(0).getGraphics());
        }
        nextBuffer();
        this.canvas.repaint();
    }

    public void nextBuffer(){
        if(buffers.size() > 1){
            buffers.remove(0);
            buffers.add(new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_RGB));
        }
    }

    public void paint(Graphics g){
        if(ready) {
            g.drawImage(buffers.get(0), 0, 0, null);
        }
    }
}
