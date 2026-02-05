package uk.ac.bucks.willralph.mmmidi.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Player extends Page{

    private VBox bounds;


    @Override
    protected Parent setLayout() {
        Label hello = new Label("Hello world");
        hello.textAlignmentProperty().setValue(TextAlignment.JUSTIFY);
        hello.setAlignment(Pos.BASELINE_CENTER);

        hello.setPadding(new Insets(20,20,20,20));

        BackgroundFill fill = new BackgroundFill(Color.BLUE, new CornerRadii(20), Insets.EMPTY);
        Background bg = new Background(fill);
        hello.setBackground(bg);

        Border b = new Border(new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.DASHED,
                CornerRadii.EMPTY,
                BorderStroke.THIN
        ));
        bounds = new VBox(hello);
        bounds.setBorder(b);

        return bounds;

    }

    private void makeLayout() {

    }
    private HBox topBar() {
        HBox nav = new HBox();

        nav.setAlignment(Pos.TOP_CENTER);
        
        return nav;
    }
    public Player() {
        super(Type.PLAYER);
    }
}
