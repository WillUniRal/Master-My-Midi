package uk.ac.bucks.willralph.mmmidi.user;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    static int total = 0;
    Note(Type col) {

        switch (col) {
           case WHITE -> whiteStyle();
           case BLACK -> blackStyle();
           case GAP -> invis();
       }

        this.setMaxWidth(Double.MAX_VALUE);

        GridPane.setHgrow(this,Priority.ALWAYS);
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

        //this.setStyle("-pressed");

    }
    private void blackStyle() {
        total++;
        //this.setMinWidth(5);
        this.setMaxHeight(60);
        this.setBorder(blackNoteBorder);

        //halfWidth();

        this.setStyle("-fx-background-color: #000000;");
    }
    private void halfWidth() {
        double width = (App.getSize().getWidth() / Piano.getTotalKeys())/2;
        this.setMinWidth(width);
    }
    private void invis(){
        total++;
        //halfWidth();
        //this.setBorder(whiteNoteBorder);
        this.setMaxHeight(60);
        System.out.println("---- I:"+total);
        this.setVisible(false);
    }
    private void showWidth() {
        System.out.println(this.widthProperty());
        System.out.println(this.isSnapToPixel());


    }

}
