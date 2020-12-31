package Launch;

import Controller.ArchController;
import View.Window;

public class Launch {

    public static void main(String[] args) throws InterruptedException {
        ArchController archController = new ArchController();
        archController.init();
        while(true){
            archController.update();
        }
    }
}
