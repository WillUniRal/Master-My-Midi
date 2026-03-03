package uk.ac.bucks.willralph.mmmidi.user;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Scale;
import uk.ac.bucks.willralph.mmmidi.App;

import java.util.*;

public class Piano extends StackPane {
    public static final GridPane BLACK_NOTES = new GridPane();
    public static final GridPane WHITE_NOTES = new GridPane();

    public static final Border blackBorder = new Border ( new BorderStroke(
          Color.BLACK,
          BorderStrokeStyle.SOLID,
          CornerRadii.EMPTY,
          BorderStroke.THIN
    ));

    private final int WHITE_SIZE = 20;
    private final int BLACK_SIZE = 10;

    private List<Note> notes = new ArrayList<>();

    private static int octaves =5;
    public final static int OCTAVE=7; // repeats at 8

    private static final Integer[] GAP_VALUES = new Integer[] {0,2,4,5,6,8,10,12,13};
    private final Set<Integer> BLACK_GAPS = new HashSet<>(Arrays.asList(GAP_VALUES));

    private final int DIV_COUNT = 14;

    private boolean endGap = false;
    private boolean isGap() {
        boolean result = BLACK_GAPS.contains(noteCount % DIV_COUNT) || endGap;
        endGap = false;
        return result;
    }

    Piano(int oct) {

        this.setMinHeight(100);
        octaves = oct;
        this.setWidth(Double.MAX_VALUE);
        this.setHeight(100);

        generateOctaves();

        this.getChildren().add(WHITE_NOTES);
        this.getChildren().add(BLACK_NOTES);

        scale();

        this.setBorder(blackBorder);
        System.out.println(this.getHeight()+" "+this.getWidth());
    }
    private void scale() {
        WHITE_NOTES.setPrefWidth(App.getSize().getWidth());
        BLACK_NOTES.setPrefWidth(App.getSize().getWidth());

        GridPane.setHgrow(WHITE_NOTES,Priority.ALWAYS);
        GridPane.setHgrow(BLACK_NOTES,Priority.ALWAYS);

        RowConstraints constraints = new RowConstraints();
        constraints.setValignment(VPos.TOP);
        BLACK_NOTES.getRowConstraints().add(constraints);

        WHITE_NOTES.setSnapToPixel(false);
        BLACK_NOTES.setSnapToPixel(false);
        this.setSnapToPixel(false);

        BLACK_NOTES.gridLinesVisibleProperty().setValue(true);
        BLACK_NOTES.setMaxHeight(60);
        BLACK_NOTES.setAlignment(Pos.TOP_CENTER);
        this.setAlignment(Pos.TOP_CENTER);

    }
    private int noteCount = 0;
    private static final int END_NOTES_OFFSET = 1;
    private void generateOctaves() {
        //makeBlackGap();
        int keyboardLength = getTotalKeys();
        for (int i=0; i < keyboardLength; i++) {
            System.out.println(i);
            makeWhite(i+1);
            if(i+1==keyboardLength) endGap = true;
            makeBlack();
        }
    }
    int whiCount = 0;
    private void makeBlackGap() {
        BLACK_NOTES.addColumn(noteCount,new Note(Note.Type.GAP));
    }
    private void makeWhite(int column) {
        noteCount++;

        makeBlackGap(); // for every white key there is a black gap

        Note newNote = new Note(Note.Type.WHITE);
        WHITE_NOTES.addColumn(column,newNote);

        notes.add(newNote);

        whiCount++;
        System.out.println("W:"+whiCount);
    }
    int blakCount = 0;
    private void makeBlack() {
        boolean make = !isGap();
        //if (Note.total == (octaves*DIV_COUNT)-1) return;
        noteCount++;
        if(make) {
            Note newNote = new Note(Note.Type.BLACK);
            BLACK_NOTES.addColumn(noteCount,newNote);
            notes.add(newNote);
            blakCount++;
            System.out.println("B:"+blakCount);
        } else BLACK_NOTES.addColumn(noteCount);
    }
    public static int getTotalKeys() {
        return octaves * OCTAVE + END_NOTES_OFFSET;
    }

    public void setOctaves(int octaves) {
        Piano.octaves = octaves;
    }
}
