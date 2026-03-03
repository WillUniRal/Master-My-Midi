package uk.ac.bucks.willralph.mmmidi;

//import java.awt.*;
//import javax.swing.*;
import javafx.application.Application;
import javafx.event.EventType;
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

    private static Parent layout;

    public static Dimension2D getSize() {
        return size;
    }

    public App() {
        super();
        setDimension(600,400);

        changeLayout();
        mainStage = new Stage();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.forceIntegerRenderScaleProperty().setValue(false);
        var label = new Label("This is a JavaFX Application");

        Scene scene = new Scene(layout, size.getWidth(), size.getHeight(),false, SceneAntialiasing.BALANCED);
        stage.setForceIntegerRenderScale(false);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            System.out.println("A close request has been made");
            System.exit(0);
        });
    }
    public static void changeLayout() {
        Page newPage = new Player();
        App.layout = newPage.getLayout();
    }

    public void render() {
        // re render
    }
    public static void setDimension(int width,int height) {
        size = new Dimension2D(width, height);
    }
}