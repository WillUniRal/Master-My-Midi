package uk.ac.bucks.willralph.mmmidi.user;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// Note Visualizer Box
public class NoteAnim extends NoteBox {
    // this is the styler of the falling notes
    NoteAnim(Type col) {
        super(col);
        initScale();
        setAlignment(Pos.BOTTOM_CENTER);
        //setPadding(new Insets(6,6,6,6));
    }
    protected void initScale() {
        GridPane.setHgrow(this, Priority.ALWAYS);
        System.out.println("hi");
        this.setMinHeight(10);
        this.setHeight(10);
        this.setMinWidth(10);

    }
    public void getPos() {
        System.out.println(getLayoutX());
        System.out.println(getLayoutY());
    }

    @Override
    protected void whiteStyle() {
        String WHITE_COLOUR = "-fx-background-color: #5045ed;";
        setStyle(WHITE_COLOUR);
    }

    @Override
    protected void blackStyle() {
        String BLACK_COLOUR = "-fx-background-color: #6720a1;";
        setStyle(BLACK_COLOUR);
    }

    @Override
    protected void invis() {
        this.setVisible(false);
    }
}
