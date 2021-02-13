package Controller.System;

import Controller.SystemManager;
import Model.PlayerEntity;
import Model.UIEntity;
import Model.World;

public class ModelSystem extends System{

    public World world;

    public ModelSystem(SystemManager sysManager){
        super(sysManager, SystemType.MODEL);

        this.world = new World(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void init() {
        fillWorld();
    }

    private void fillWorld() {
        world.addEntity(new PlayerEntity(world, "player_1", 20, 120, 1, 10));
        //world.addEntity(new PlayerEntity(world, "player_2", 100, 40, 2, 10));

        world.addEntity(new UIEntity(world, "button1", 100, 20, 100, 20));
        world.addEntity(new UIEntity(world, "button2", 100, 50, 100, 20));
        world.addEntity(new UIEntity(world, "button3", 100, 80, 100, 20));
        world.addEntity(new UIEntity(world, "button4", 100, 110, 100, 20));

    }
}
