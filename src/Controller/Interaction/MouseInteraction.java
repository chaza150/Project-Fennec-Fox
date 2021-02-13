package Controller.Interaction;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MouseInteraction implements MouseListener, MouseMotionListener, MouseWheelListener {

    ConcurrentHashMap<Integer, Boolean> buttonsDown = new ConcurrentHashMap<>();
    boolean mousePresent = false;

    ConcurrentLinkedQueue<MouseEvent> mouseClicks = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<MouseEvent> lastFrameMouseClicks = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<MouseWheelEvent> mouseWheelEvents = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<MouseWheelEvent> lastFrameMouseWheelEvents = new ConcurrentLinkedQueue<>();

    Point currentMouseCoords = new Point(0, 0);

    boolean dragged = false;
    Point mouseDragOrigin = null;
    Point mouseDragDistance = null;

    public MouseInteraction(){
        buttonsDown.put(MouseEvent.BUTTON1, false);
        buttonsDown.put(MouseEvent.BUTTON2, false);
        buttonsDown.put(MouseEvent.BUTTON3, false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mousePresent = true;
        synchronized (this){
            mouseClicks.add(e);
        }
        currentMouseCoords = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonsDown.put(e.getButton(), true);
        currentMouseCoords.setLocation(e.getX(), e.getY());
        mouseDragDistance = new Point(0,0);
        mouseDragOrigin = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePresent = true;
        buttonsDown.put(e.getButton(), false);
        currentMouseCoords = e.getPoint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePresent = true;
        currentMouseCoords = e.getPoint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePresent = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragged = true;
        synchronized (this) {
            if (mouseDragOrigin == null) {
                mouseDragOrigin = e.getPoint();
            }
            mouseDragDistance = new Point((int) (e.getX() - mouseDragOrigin.getX()), (int) (e.getY() - mouseDragOrigin.getY()));
        }
        currentMouseCoords = e.getPoint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentMouseCoords = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        synchronized (this) {
            mouseWheelEvents.add(e);
        }
    }

    public ArrayList<MouseEvent> getMouseClicks(){
        return new ArrayList<>(lastFrameMouseClicks);
    }

    public ArrayList<MouseWheelEvent> getMouseWheelEvents(){
        return new ArrayList<>(lastFrameMouseWheelEvents);
    }

    public Point getCurrentMouseCoords(){
        return currentMouseCoords;
    }

    public boolean isDragging(){
        return dragged;
    }

    public boolean isMouseButtonDown(int buttonIndex){
        return buttonsDown.get(buttonIndex);
    }

    public boolean isMousePresent(){
        return mousePresent;
    }

    public Point getMouseDragDistance(){
        return mouseDragDistance;
    }

    public void update(){

        lastFrameMouseClicks = new ConcurrentLinkedQueue<>();
        synchronized (this){
            lastFrameMouseClicks.addAll(mouseClicks);
            mouseClicks = new ConcurrentLinkedQueue<>();
        }

        lastFrameMouseWheelEvents = new ConcurrentLinkedQueue<>();
        synchronized (this) {
            lastFrameMouseWheelEvents.addAll(mouseWheelEvents);
            mouseWheelEvents = new ConcurrentLinkedQueue<>();
        }

        dragged = false;
        synchronized (this) {
            mouseDragOrigin = null;
        }

        mouseDragDistance = null;
    }


}
