package uk.ac.bucks.willralph.mmmidi.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

// Note Visualizer Box
public class NoteAnim extends NoteBox {
    // this is the styler of the falling notes
    NoteAnim(Type col) {
        super(col);
        initScale();
        callStyle();

    }
    protected void initScale() {
        setAlignment(Pos.BOTTOM_CENTER);
        //GridPane.setHgrow(this, Priority.ALWAYS);
        setMaxHeight(0);
        this.setHeight(1000);
    }

    @Override
    protected void whiteStyle() {
        String WHITE_COLOUR = "-fx-background-color: #5045ED;";
        setStyle(WHITE_COLOUR);
        setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
    }

    @Override
    protected void blackStyle() {
        // 6720A1
        setBorder(new Border(new BorderStroke(Color.BLUE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        setStyle("-fx-background-color: #6720A1;");
    }

    @Override
    protected void invis() {
        this.setVisible(false);
    }
}
