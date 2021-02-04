package Controller.System;

import Controller.SystemManager;
import Model.PlayerEntity;
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
        world.addEntity(new PlayerEntity(world, "player_1", 20, 20));
    }
}
