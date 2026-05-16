package uk.ac.bucks.willralph.mmmidi.user.nodes;

import javafx.scene.layout.VBox;

public abstract class NoteBox extends VBox {

    public enum Type {
        BLACK,
        GAP,
        WHITE
    }
    protected int value =0;
    protected Type COLOUR;
    public int getValue(){
        return value;
    }

    NoteBox(Type col) {
        COLOUR = col;
    }
    protected void callStyle() {
        switch (COLOUR) {
            case WHITE -> whiteStyle();
            case BLACK -> blackStyle();
            case Type.GAP -> invis();
        }
    }
    protected abstract void whiteStyle();
    protected abstract void blackStyle();
    protected abstract void invis();
}
