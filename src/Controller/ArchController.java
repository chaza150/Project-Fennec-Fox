package Controller;

import Controller.Model.ModelController;

public class ArchController extends Controller{

    public ViewController viewController;
    public ModelController modelController;
    public InteractionController interactionController;

    long previousUpdateTime = 0;
    public int deltaTime = 0;

    public ArchController(){
        this.viewController = new ViewController(this);
        this.interactionController = new InteractionController(this);
        this.modelController = new ModelController(this);
    }

    public void init(){
        this.modelController.init();
        this.viewController.init();
        this.interactionController.init();
    }

    public void update(){
        updateTime();
        this.viewController.update();
        this.modelController.update();
        this.interactionController.update();
    }

    public void updateTime(){
        if(previousUpdateTime == 0){
            deltaTime = 0;
            previousUpdateTime = System.currentTimeMillis();
        } else {
            deltaTime = (int)(System.currentTimeMillis() - previousUpdateTime);
            previousUpdateTime = System.currentTimeMillis();
        }
    }
}
