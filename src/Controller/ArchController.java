package Controller;

import Controller.Model.ModelController;

public class ArchController extends Controller{

    public ViewController viewController;
    public ModelController modelController;
    public InteractionController interactionController;

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
        this.viewController.update();
        this.modelController.update();
        this.interactionController.update();
    }
}
