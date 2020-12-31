package View;

import Controller.Interaction.KeyboardInteraction;

import javax.swing.*;

public class Window extends JFrame {

    KeyboardInteraction keyInteraction;

    public Window(int width, int height, String title) {
        super(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}