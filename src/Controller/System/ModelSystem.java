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
        world.addEntity(new PlayerEntity(world, "player_1", 20, 120, 1, 10));
        world.addEntity(new PlayerEntity(world, "player_2", 100, 40, 2, 10));

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                world.addEntity(new PlayerEntity(world, "player_" + i + j, i*20, j*20, 2, 5));
            }
        }
    }
}
