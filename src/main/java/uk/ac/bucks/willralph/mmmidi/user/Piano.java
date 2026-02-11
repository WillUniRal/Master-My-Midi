package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Piano extends HBox {
    private HBox blackNotes;
    private HBox whiteNotes;

    private final int WHITE_SIZE = 20;
    private final int BLACK_SIZE = 10;

    private List<Note> notes;

    private int octaves =5;
    private final int OCTAVE=8;

    private static final Integer[] GAP_VALUES = new Integer[] {0,2,4,6,7,8,10,12,13};
    private final Set<Integer> BLACK_GAPS = new HashSet<>(Arrays.asList(GAP_VALUES));

    private boolean isGap() {
        int divCount = 13;
        return BLACK_GAPS.contains(noteCount % divCount);
    }

    Piano(int oct) {
        octaves = oct;
    }
    private int noteCount = 0;
    private void generateOctaves() {
        for (int i=0; i < octaves; i++) {
            makeWhite();
            makeBlack();
        }
    }
    private void makeWhite() {
        noteCount++;
        Note newNote = new Note(Note.Type.WHITE);
        whiteNotes.getChildren().add(newNote);

        notes.add(newNote);
    }
    private void makeBlack() {
        boolean make = !isGap();

        noteCount++;
        Note.Type gap = !make ? Note.Type.GAP : Note.Type.BLACK;
        Note newNote = new Note(gap);
        blackNotes.getChildren().add(newNote);

        if(make) notes.add(newNote);
    }

    public int getOctaves() {
        return octaves;
    }

    public void setOctaves(int octaves) {
        this.octaves = octaves;
    }
}
