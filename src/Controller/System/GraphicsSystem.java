package Controller.System;

import Controller.Settings;
import Controller.SystemManager;
import Model.Component.ComponentType;
import Model.Component.Component;
import Model.Entity;
import View.Canvas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class GraphicsSystem extends System{

    Canvas canvas;

    ArrayList<BufferedImage> buffers;

    boolean ready = false;

    public GraphicsSystem(SystemManager sysManager){
        super(sysManager, SystemType.GRAPHICS);
        setPrerequisites(SystemType.VIEW);
        setInitPrerequisites(SystemType.VIEW);
        setUpdatePrerequisites(SystemType.VIEW);

        this.canvas = sysManager.<ViewSystem>getSystem(SystemType.VIEW).canvas;
        this.buffers = new ArrayList<>();

    }

    @Override
    public void update() {
        if(buffers.size() > 1) {
            paintBuffer(this.buffers.get(1).getGraphics());
        } else {
            paintBuffer(this.buffers.get(0).getGraphics());
        }
        nextBuffer();
        this.canvas.repaint();
    }

    @Override
    public void init() {
        for(int i = 0; i < Settings.BUFFER_COUNT; i++){
            this.buffers.add(new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_RGB));
        }
        this.canvas.setGraphicsSystem(this);
        ready = true;
    }

    public void nextBuffer(){
        if(buffers.size() > 1){
            buffers.remove(0);
            buffers.add(new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_RGB));
        }
    }

    public void paint(Graphics g){
        if(ready) {
            g.drawImage(buffers.get(0), 0, 0, null);
        }
    }

    public void paintBuffer(Graphics g){

        HashMap<Entity, Component> graphicsComponents = sysManager.<ModelSystem>getSystem(SystemType.MODEL).world.getComponents(ComponentType.GRAPHICS);
        for(Entity entity : graphicsComponents.keySet()){
            graphicsComponents.get(entity).<Consumer<Graphics>>getProperty("painter").accept(g);
        }

    }
}
