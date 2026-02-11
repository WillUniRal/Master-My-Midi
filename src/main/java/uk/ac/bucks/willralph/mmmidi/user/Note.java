package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Note extends VBox {
    private final Border whiteNoteBorder = new Border(new BorderStroke(
            Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderStroke.THIN
    ));
    enum Type {
        BLACK,
        GAP,
        WHITE
    }
    private Type colour;
    Note(Type col) {
       colour = col;
       switch (colour) {
           case WHITE -> whiteStyle();
           case BLACK -> blackStyle();
           case GAP -> invis();
       }
    }
    private void whiteStyle() {

    }
    private void blackStyle() {

    }
    private void invis(){

    }

}
