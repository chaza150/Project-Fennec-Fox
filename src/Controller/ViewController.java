package Controller;

import View.Canvas;
import View.Graphics.Painter;
import View.Window;

public class ViewController extends Controller{

    public ArchController archController;
    Window window;
    Canvas canvas;

    public GraphicsController graphicsController;

    public ViewController(ArchController archController){
        this.archController = archController;
        this.window = new Window(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, Settings.WINDOW_TITLE);
        this.canvas = new Canvas(this);
        this.window.setContentPane(this.canvas);
        this.graphicsController = new GraphicsController(this);

    }

    public void init(){
        this.window.setVisible(true);
        this.canvas.setFocusable(true);
        this.canvas.addKeyListener(archController.interactionController.keyboardInteraction);
        this.graphicsController.init();
    }

    @Override
    public void update() {
        this.graphicsController.update();
        this.canvas.repaint();
    }
}
