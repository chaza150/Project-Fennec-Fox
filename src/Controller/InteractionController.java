package Controller;

import Controller.Interaction.KeyboardInteraction;

public class InteractionController extends Controller{

    ArchController archController;
    KeyboardInteraction keyboardInteraction;

    public InteractionController(ArchController archController){
        this.archController = archController;
        this.keyboardInteraction = new KeyboardInteraction(Settings.TYPE_WINDOW_MILLIS);
    }

    public void init(){}

    @Override
    public void update() {
        keyboardInteraction.update();
        if(keyboardInteraction.wasKeyTyped('t')){
            System.out.println("t");
        }
    }
}
