package Controller.Interaction;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MouseInteraction implements MouseListener, MouseMotionListener, MouseWheelListener {

    boolean mouseDown = false;
    boolean mousePresent = false;

    ConcurrentLinkedQueue<MouseEvent> mouseClicks = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<MouseWheelEvent> mouseWheelEvents = new ConcurrentLinkedQueue<>();

    Point currentMouseCoords = new Point(0, 0);

    boolean dragged = false;
    Point mouseDragOrigin = null;
    Point mouseDragDistance = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        mousePresent = true;
        mouseClicks.add(e);

        currentMouseCoords = e.getPoint();

        System.out.println("Mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        currentMouseCoords.setLocation(e.getX(), e.getY());
        mouseDragDistance = new Point(0,0);
        mouseDragOrigin = e.getPoint();

        System.out.println("Mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePresent = true;
        mouseDown = false;
        currentMouseCoords = e.getPoint();
        System.out.println("Mouse Released");

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
        if(mouseDragOrigin == null){
            mouseDragOrigin = e.getPoint();
        }
        mouseDragDistance = new Point((int)(e.getX()-mouseDragOrigin.getX()), (int)(e.getY()-mouseDragOrigin.getY()));
        currentMouseCoords = e.getPoint();
        System.out.println("Mouse Dragged");

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse Moved");
        currentMouseCoords = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseWheelEvents.add(e);
        System.out.println("Mouse Wheel Event");
    }

    public ArrayList<MouseEvent> getMouseClicks(){
        return new ArrayList<>(mouseClicks);
    }

    public ArrayList<MouseWheelEvent> getMouseWheelEvents(){
        return new ArrayList<>(mouseWheelEvents);
    }

    public Point getCurrentMouseCoords(){
        return currentMouseCoords;
    }

    public boolean isDragging(){
        return dragged;
    }

    public boolean isMouseDown(){
        return mouseDown;
    }

    public boolean isMousePresent(){
        return mousePresent;
    }

    public Point getMouseDragDistance(){
        return mouseDragDistance;
    }

    public void update(){
        mouseClicks = new ConcurrentLinkedQueue<>();
        mouseWheelEvents = new ConcurrentLinkedQueue<>();
        dragged = false;
        mouseDragOrigin = null;
        mouseDragDistance = null;
    }


}
