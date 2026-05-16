package uk.ac.bucks.willralph.mmmidi.user.nodes;

import javafx.scene.layout.*;
import uk.ac.bucks.willralph.picom.SerialCom.Method;

import java.awt.Color;

public class Note extends NoteBox {


    private final Piano PIANO;
    private final BorderWidths borderWidth = new BorderWidths(1);
    private static int noteInt = 36;
    private final NoteQueue QUEUE;

    private final Border whiteNoteBorder = new Border(new BorderStroke(
            javafx.scene.paint.Color.BLACK,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            BorderStroke.THIN
    ));
    private final Border blackNoteBorder = new Border(new BorderStroke(
            javafx.scene.paint.Color.gray(0.5),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            borderWidth
    ));

    public void beginCount(int start) {
        noteInt = start;
    }
    public void noteCount() {
        value = noteInt;
        noteInt++;
    }
    Note(Type col, Piano piano, NoteQueue queue) {
        super(col);
        PIANO = piano;
        QUEUE = queue;


        callStyle();
        if(COLOUR!=Type.GAP) noteCount();

        stretch();

        GridPane.setVgrow(this,Priority.ALWAYS);
        GridPane.setFillWidth(this,true);
        GridPane.setFillHeight(this,true);

        this.setOnMousePressed(e -> on());
        this.setOnMouseReleased(e -> off());

        this.setSnapToPixel(false);
    }
    @Override
    protected void whiteStyle() {
        this.setHeight(50.0);
        this.setBorder(whiteNoteBorder);
        this.setStyle(WHITE_COLOUR);
    }
    @Override
    protected void blackStyle() {
        this.setMaxHeight(60);
        this.setBorder(blackNoteBorder);
        this.setStyle(BLACK_COLOUR);
    }
    private final String WHITE_COLOUR = "-fx-background-color: #FFFFFF;";
    private final String BLACK_COLOUR = "-fx-background-color: #000000;";

    public void stretch() {
        if(COLOUR==Type.WHITE) {
            this.setMinWidth(PIANO.getWhiteWidth());
            QUEUE.setMinWidth(PIANO.getWhiteWidth());
        } else {
            this.setMinWidth(PIANO.getBlackWidth());
            QUEUE.setMinWidth(PIANO.getBlackWidth());
        }
    }
    @Override
    protected void invis(){
        this.setMinWidth(PIANO.getBlackWidth());
        this.setVisible(false);
    }
    private void on() {
        Piano.sounds.playNote(value,120,0);

        pressed();
    }
    private void off() {
        //Piano.sounds.stopNote(value,120,0);

        unpressed();
    }

    public void pressed() {
        if(COLOUR == Type.WHITE) {
            this.setStyle("-fx-background-color: #808080;");
            PIANO.SERIAL.sendData(Method.ON, value, Color.RED);
        } else {
            this.setStyle("-fx-background-color: #2565BA;");
            PIANO.SERIAL.sendData(Method.ON, value, Color.BLUE);
        }
        QUEUE.startAnim();
    }
    public void unpressed() {
        if (COLOUR == Type.WHITE)
            this.setStyle(WHITE_COLOUR);
        else
            this.setStyle(BLACK_COLOUR);
        PIANO.SERIAL.sendData(Method.OFF,value);
        QUEUE.stopAnim();
    }
}
