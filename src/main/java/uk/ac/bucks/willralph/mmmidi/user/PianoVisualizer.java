package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PianoVisualizer extends PianoGrid{
    /*
        The visualizer is what contains the Stack of GridPanes

     */

    public PianoVisualizer() {
        super();
        VBox.setVgrow(this,Priority.ALWAYS);
        WHITE_NOTES.setGridLinesVisible(true);
        //BLACK_NOTES.setGridLinesVisible(true);
    }

    @Override
    protected void initScale() {
        super.initScale();
        this.setWidth(Double.MAX_VALUE);
        this.setMinHeight(200);
        this.setHeight(Double.MAX_VALUE);
    }

    private NoteQueue currentNote;
    public void setCurrentNote(int column, NoteQueue note) {
        currentNote = note;
        switch (currentNote.COLOUR) {
            case WHITE ->  makeWhite(column);
            case BLACK -> makeBlack(column);
            case GAP -> makeBlackGap(column);
        }
    }


    @Override
    protected void makeWhite(int... column) {
        WHITE_NOTES.addColumn(column[0], currentNote);
    }
    @Override
    protected void makeBlack(int... column) {
        BLACK_NOTES.addColumn(column[0], currentNote);
    }
    @Override
    protected void makeBlackGap(int... column) {
        makeBlack(column);
    }
}
