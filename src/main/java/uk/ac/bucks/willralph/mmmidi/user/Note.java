package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Note extends HBox {
    private final Border whiteNoteBorder = new Border(new BorderStroke(
            Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderStroke.THIN
    ));
    private final Border blackNoteBorder = new Border(new BorderStroke(
            Color.gray(0.4),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderStroke.THIN
    ));
    enum Type {
        BLACK,
        GAP,
        WHITE
    }
    static int total = 0;
    Note(Type col) {

        switch (col) {
           case WHITE -> whiteStyle();
           case BLACK -> blackStyle();
           case GAP -> invis();
       }
        //this.setMinHeight(20);

        HBox.setHgrow(this,Priority.ALWAYS);
    }
    private void whiteStyle() {
        this.setMinWidth(10);
        this.setHeight(50.0);
        this.setBorder(whiteNoteBorder);
        this.setStyle("-fx-background-color: #FFFFFF;");

    }
    private void blackStyle() {
        total++;
        this.setMinWidth(5);
        this.setMaxHeight(60);
        this.setBorder(blackNoteBorder);
        this.setStyle("-fx-background-color: #000000;");
    }
    private void invis(){
        total++;
        this.setMinWidth(5);
        //this.setBorder(whiteNoteBorder);
        this.setMaxHeight(60);
        System.out.println("---- I:"+total);
        this.setVisible(false);
    }

}
