package Controller.Model;

import Controller.*;
import Model.World;

public class ModelController extends Controller{

    World world;

    ArchController archController;

    public ModelController(ArchController archController){
        this.archController = archController;
        this.world = new World(this);
    }

    public void init(){

    }

    public void update() {
        this.world.update();
    }
}
