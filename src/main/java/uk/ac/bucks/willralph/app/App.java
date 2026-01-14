package uk.ac.bucks.willralph.app;

import java.awt.*;
import javax.swing.*;

public class App {
    private Dimension dimension = new Dimension(0,0);
    private JFrame frame = new JFrame("Master my midi");

    App() {
        setDimension(600,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void render() {
        frame.repaint();
    }
    private void setDimension(int width,int height) {
        dimension.width = width;
        dimension.height = height;
        frame.setSize(dimension);
    }
}
