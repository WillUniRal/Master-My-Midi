package uk.ac.bucks.willralph.mmmidi;

//import java.awt.*;
//import javax.swing.*;
import javafx.application.Application;
import javafx.geometry.Dimension2D;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uk.ac.bucks.willralph.mmmidi.user.Page;
import uk.ac.bucks.willralph.mmmidi.user.Player;


public class App extends Application {
    //private JFrame frame = new JFrame("Master my midi");

    private static Scene currentScene;
    private static Dimension2D size;
    public Stage mainStage;

    public static Dimension2D getSize() {
        return size;
    }

    public App() {
        super();
        setDimension(600,400);
        mainStage = new Stage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        var label = new Label("This is a JavaFX Application");

        Page newPage = new Player();
        Parent layout = newPage.getLayout();

        Scene scene = new Scene(layout, size.getWidth(), size.getHeight(),false, SceneAntialiasing.BALANCED);
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
    public static void setDimension(int width,int height) {
        size = new Dimension2D(width, height);
    }
}