package uk.ac.bucks.willralph.mmmidi;

//import java.awt.*;
//import javax.swing.*;
import javafx.application.Application;
import javafx.geometry.Dimension2D;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Screen;
import javafx.stage.Stage;
import uk.ac.bucks.willralph.mmmidi.user.pages.Page;
import uk.ac.bucks.willralph.mmmidi.user.pages.Player;
import uk.ac.bucks.willralph.mmmidi.user.pages.Settings;


public class App extends Application {
    //private JFrame frame = new JFrame("Master my midi");

    private static Scene currentScene;
    private static Dimension2D size = setDimension(600,400);
    public static Stage mainStage;

    private static Parent layout;

    public static Dimension2D getSize() {
        if (mainStage!=null)
            setDimension((int) mainStage.getWidth(), (int) mainStage.getHeight());
        return size;
    }

    public App() {
        super();
        changeLayout(Page.Type.PLAYER);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        setCurrentScene();

        System.out.println("screensize: "+Screen.getPrimary().getDpi());

        mainStage.setOnShowing(e -> onShow());
        mainStage.show();

        mainStage.setOnCloseRequest(e -> {
            System.out.println("A close request has been made");
            System.exit(0);
        });
    }
    public static void setCurrentScene() {
        mainStage.hide();
        Scene scene = new Scene(layout, size.getWidth(), size.getHeight(),true, SceneAntialiasing.BALANCED);
        mainStage.setForceIntegerRenderScale(false);
        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void changeLayout(Page.Type type) {
        Page newPage;
        switch (type) {
            case SETTINGS -> {newPage = new Settings();}
            case PLAYER -> {newPage = new Player();}
            default ->  {newPage = new Player();} // temporary
        }
        App.layout = newPage.getLayout();

    }
    private void onShow() {
        System.out.println("integerScaling: "+mainStage.forceIntegerRenderScaleProperty());
        Player.setListeners();
        //mainStage.widthProperty().addListener();
    }
    int showings = 0;
    public void render() {
        // re-render
        System.out.println("show:"+showings);
    }
    public static Dimension2D setDimension(int width,int height) {
        size = new Dimension2D(width,height);
        if (mainStage!=null) {
            mainStage.setWidth(width);
            mainStage.setHeight(height);
        } return size;
    }
}