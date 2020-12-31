package Controller.Interaction;

import Helper.Helper;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class KeyboardInteraction implements KeyListener {

    HashSet<Integer> keysTyped = new HashSet<>();

    private ConcurrentHashMap<Integer, Long> frameKeysDown = new ConcurrentHashMap<>();
    private HashSet<Integer> frameKeysTimedOut = new HashSet<>();
    HashSet<Integer> keysDown = new HashSet<>();

    int typeWindow;

    public KeyboardInteraction(int typeWindow){
        this.typeWindow = typeWindow;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!keysDown.contains(e.getExtendedKeyCode()) && frameKeysTimedOut.contains(e.getExtendedKeyCode())){
            frameKeysTimedOut.remove(e.getExtendedKeyCode());
        }
        if(!keysDown.contains(e.getExtendedKeyCode()) && !frameKeysTimedOut.contains(e.getExtendedKeyCode())) {
            //System.out.println(e.getExtendedKeyCode() + " Down");
            frameKeysDown.put(e.getExtendedKeyCode(), System.currentTimeMillis());
        }

        keysDown.add(e.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysDown.remove(e.getExtendedKeyCode());
        if(frameKeysDown.containsKey(e.getExtendedKeyCode()) && !frameKeysTimedOut.contains(e.getExtendedKeyCode())){
            //System.out.println(e.getExtendedKeyCode() + " Typed");
            keysTyped.add(e.getExtendedKeyCode());
        }
        //System.out.println(e.getExtendedKeyCode() + " Up");
        frameKeysTimedOut.remove(e.getExtendedKeyCode());
    }

    public void update(){
        keysTyped = new HashSet<>();
        long currentTime = System.currentTimeMillis();
        for(Integer key : frameKeysDown.keySet()){
            long timer = frameKeysDown.get(key);
            if (currentTime > timer + typeWindow) {
                frameKeysTimedOut.add(key);
                frameKeysDown.remove(key);
                //System.out.println(key + " Timed Out");
            }
        }
    }

    public boolean isKeyDown(char c){
        return keysDown.contains(KeyEvent.getExtendedKeyCodeForChar(c));
    }

    public boolean wasKeyTyped(char c){
        return keysTyped.contains(KeyEvent.getExtendedKeyCodeForChar(c));
    }
}
