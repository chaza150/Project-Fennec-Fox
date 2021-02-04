package Launch;

import Controller.SystemManager;

public class Launch {

    public static void main(String[] args) throws InterruptedException {
        SystemManager sysManager = new SystemManager();
        sysManager.init();
        while(true){
            sysManager.update();
        }
    }
}
