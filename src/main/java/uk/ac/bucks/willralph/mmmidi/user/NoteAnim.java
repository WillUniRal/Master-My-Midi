package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
// Note Visualizer Box
public class NoteVBox extends NoteBox {
    // this is the styler of the falling notes
    NoteVBox(Type col) {
        super(col);
        initScale();
    }
    protected void initScale() {
        this.setMaxHeight(Double.MAX_VALUE);
        this.setHeight(0);
    }

    @Override
    protected void whiteStyle() {

    }

    @Override
    protected void blackStyle() {

    }

    @Override
    protected void invis() {
        this.setVisible(false);
    }
}
