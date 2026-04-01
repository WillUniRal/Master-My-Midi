package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PianoVisualizer extends PianoGrid{

    public PianoVisualizer() {
        super();
    }
    private NoteQueue currentNote;
    public void setCurrentNote(int column,NoteQueue note) {
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
