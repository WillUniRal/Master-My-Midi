package uk.ac.bucks.willralph.mmmidi;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;

public class HelloController {
    @FXML
    private final Menu menuTest = new Menu("Hellooo");
    @FXML
    private final MenuBar navbar = new MenuBar();
    @FXML
    private final BackgroundFill fill = new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY);
    @FXML
    private Background bg = new Background(fill);

    @FXML
    private Label welcomeText;
    HelloController() {
        navbar.getMenus().add(menuTest);
        try {
            navbar.setBackground(bg);
        } catch (NullPointerException _) {}

    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}
