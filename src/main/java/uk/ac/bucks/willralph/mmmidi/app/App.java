package uk.ac.bucks.willralph.mmmidi.app;

//import java.awt.*;
//import javax.swing.*;
import javafx.application.Application;
import javafx.geometry.Dimension2D;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {
    //private JFrame frame = new JFrame("Master my midi");

    private static Scene currentScene;
    private Dimension2D size;
    public Stage mainStage;

    App() {
        setDimension(600,400);
        mainStage = new Stage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        var label = new Label("This is a JavaFX Application");
        VBox layout = new VBox(label);

        Scene scene = new Scene(layout, size.getWidth(), size.getHeight());
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> {
            System.out.println("A close request has been made");
            System.exit(0);
        });
    }

    public void render() {
        // re render
    }
    public void setDimension(int width,int height) {
        size = new Dimension2D(width, height);
    }
}
