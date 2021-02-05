package Controller.System;

import Controller.Settings;
import Controller.SystemManager;
import View.Canvas;
import View.Window;

public class ViewSystem extends System {

    Window window;
    Canvas canvas;

    public ViewSystem(SystemManager sysManager){
        super(sysManager, SystemType.VIEW);
        setPrerequisites(SystemType.MODEL);
        setInitPrerequisites(SystemType.MODEL);
        setUpdatePrerequisites(SystemType.MODEL);

        this.window = new Window(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, Settings.WINDOW_TITLE);
        this.canvas = new Canvas(this);
        this.window.setContentPane(this.canvas);
    }

    @Override
    public void update() {
    }

    @Override
    public void init() {
        this.window.setVisible(true);
        this.canvas.setFocusable(true);
        this.canvas.addKeyListener(sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).keyboardInteraction);
        this.canvas.addMouseListener(sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).mouseInteraction);
        this.canvas.addMouseMotionListener(sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).mouseInteraction);
        this.canvas.addMouseWheelListener(sysManager.<InteractionSystem>getSystem(SystemType.INTERACTION).mouseInteraction);
    }
}
