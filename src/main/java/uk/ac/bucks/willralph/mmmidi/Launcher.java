package uk.ac.bucks.willralph.mmmidi;

import javafx.application.Application;
import javafx.scene.Parent;
import uk.ac.bucks.willralph.mmmidi.user.Page;
import uk.ac.bucks.willralph.mmmidi.user.Player;

public class Launcher {
    public static void main(String[] args) {
        // System.setProperty("glass.gtk.uiScale", "0.5");
        // System.setProperty("sun.java2d.uiScale", "1.5");

        Application.launch(App.class, args);
    }
}
