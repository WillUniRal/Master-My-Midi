package uk.ac.bucks.willralph.mmmidi;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Label hello = new Label("Hello world");
        hello.textAlignmentProperty().setValue(TextAlignment.JUSTIFY);
        hello.setAlignment(Pos.BASELINE_CENTER);

        hello.setPadding(new Insets(20,20,20,20));

        BackgroundFill fill = new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY);
        Background bg = new Background(fill);

        Parent pane = new GridPane();
        VBox layout = new VBox(hello);


        hello.setBackground(bg);

        //hello.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(layout,0.0);
        AnchorPane.setRightAnchor(layout,0.0);

        Scene scene = new Scene(layout, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        System.out.printf("layoutSize: %d",hello.getProperties().size());

    }
}
