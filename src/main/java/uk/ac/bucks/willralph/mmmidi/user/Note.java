package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uk.ac.bucks.willralph.mmmidi.App;

public class Note extends VBox {
    private final BorderWidths borderWidth = new BorderWidths(1);
    private final Border whiteNoteBorder = new Border(new BorderStroke(
            Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            borderWidth
    ));
    private final Border blackNoteBorder = new Border(new BorderStroke(
            Color.gray(0.5),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            borderWidth
    ));
    enum Type {
        BLACK,
        GAP,
        WHITE
    }
    private final Type COLOUR;
    Note(Type col) {
        COLOUR = col;

        switch (col) {
           case WHITE -> whiteStyle();
           case BLACK -> blackStyle();
           case GAP -> invis();
       }
        stretch();

        GridPane.setVgrow(this,Priority.ALWAYS);
        GridPane.setFillWidth(this,true);
        GridPane.setFillHeight(this,true);

        this.setOnMouseClicked( e -> showWidth());
        this.setSnapToPixel(false);
    }
    private void whiteStyle() {
        this.setHeight(50.0);
        this.setBorder(whiteNoteBorder);
        this.setStyle("-fx-background-color: #FFFFFF;");

    }
    private void blackStyle() {
        this.setMinWidth(Piano.getBlackWidth());
        this.setMaxHeight(60);
        this.setBorder(blackNoteBorder);
        this.setStyle("-fx-background-color: #000000;");
    }
    public void stretch() {
        if(COLOUR==Type.WHITE) {
            this.setMinWidth(Piano.getWhiteWidth());
        } else this.setMinWidth(Piano.getBlackWidth());
    }
    private void invis(){
        this.setMinWidth(Piano.getBlackWidth());
        this.setVisible(false);
    }
    private void showWidth() {
        System.out.println(this.widthProperty());
        System.out.println(this.isSnapToPixel());
    }

}
