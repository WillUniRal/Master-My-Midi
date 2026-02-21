package uk.ac.bucks.willralph.mmmidi.user;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.*;

public class Piano extends StackPane {
    private final HBox blackNotes = new HBox();
    private final HBox whiteNotes = new HBox();

    private final int WHITE_SIZE = 20;
    private final int BLACK_SIZE = 10;

    private List<Note> notes = new ArrayList<>();

    private int octaves =5;
    private final int OCTAVE=7; // repeats at 8

    private static final Integer[] GAP_VALUES = new Integer[] {0,2,4,5,6,8,10,12,13};
    private final Set<Integer> BLACK_GAPS = new HashSet<>(Arrays.asList(GAP_VALUES));

    private final int DIV_COUNT = 14;
    private boolean isGap() {

        return BLACK_GAPS.contains(noteCount % DIV_COUNT);
    }

    Piano(int oct) {

        this.setMinHeight(100);
        octaves = oct;
        this.setWidth(Double.MAX_VALUE);
        this.setHeight(100);

        generateOctaves();

        this.getChildren().add(whiteNotes);
        this.getChildren().add(blackNotes);

        this.setBorder(new Border( new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderStroke.THIN
        )));
        System.out.println(this.getHeight()+" "+this.getWidth());
    }
    private int noteCount = 0;
    private void generateOctaves() {
        makeBlackGap();
        for (int i=0; i < octaves*OCTAVE; i++) {
            System.out.println(i);
            makeWhite();
            makeBlack();
        }
        //whiteNotes.getChildren().forEach(e -> {System.out.println("found");});
    }
    int whiCount = 0;
    private void makeBlackGap() {
        blackNotes.getChildren().add(new Note(Note.Type.GAP));
    }
    private void makeWhite() {
        noteCount++;

        makeBlackGap(); // for every white key there is a black gap

        Note newNote = new Note(Note.Type.WHITE);
        whiteNotes.getChildren().add(newNote);

        notes.add(newNote);

        whiCount++;
        System.out.println("W:"+whiCount);
    }
    int blakCount = 0;
    private void makeBlack() {
        boolean make = !isGap();
        //if (Note.total == (octaves*DIV_COUNT)-1) return;
        noteCount++;

        Note.Type gap = !make ? Note.Type.GAP : Note.Type.BLACK;

        Note newNote = new Note(gap);
        blackNotes.getChildren().add(newNote);

        if(make) {
            notes.add(newNote);
            blakCount++;
            System.out.println("B:"+blakCount);
        }
    }

    public int getOctaves() {
        return octaves;
    }

    public void setOctaves(int octaves) {
        this.octaves = octaves;
    }
}
