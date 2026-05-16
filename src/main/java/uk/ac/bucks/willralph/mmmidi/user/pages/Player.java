package uk.ac.bucks.willralph.mmmidi.user.pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uk.ac.bucks.willralph.mmmidi.App;
import uk.ac.bucks.willralph.mmmidi.user.nodes.Piano;

public class Player extends Page{

    private VBox bounds;
    private final static Piano PIANO = new Piano(5);

    @Override
    protected Parent setLayout() {
        makeLayout(topBar());
        return bounds;
    }

    private HBox notes() {
        BackgroundFill fill = new BackgroundFill(Color.gray(0.7), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(fill);

        HBox placeholder = new HBox();
        placeholder.setBackground(bg);

        return placeholder;
    }

    private void makeLayout(HBox top) {
        //System.out.println(PIANO.VISUALIZER.getTranslateZ());
        Camera cam = new ParallelCamera();
        PIANO.setTranslateZ(-1);
        bounds = new VBox(cam,top,PIANO.VISUALIZER, PIANO);
        bounds.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        bounds.setSnapToPixel(false);
        PIANO.setSnapToPixel(false);
    }
    private HBox topBar() {
        HBox nav = new HBox();

        nav.setAlignment(Pos.TOP_CENTER);
        nav.setPadding(new Insets(15, 12, 15, 12));
        nav.setSpacing(10);
        nav.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Settings");
        buttonCurrent.setPrefSize(100, 20);
        buttonCurrent.setOnAction(e -> {
            App.changeLayout(Type.SETTINGS);
            App.setCurrentScene();
        });

        Label hello = new Label("Hello world");

        Button buttonProjected = new Button("Close");
        buttonProjected.setPrefSize(100, 20);

        nav.getChildren().addAll(buttonCurrent, hello, buttonProjected);

        nav.setTranslateZ(-1);
        return nav;
    }
    public static void setListeners() {
        PIANO.setListeners();
    }
    public Player() {
        super(Type.PLAYER);
    }
}
