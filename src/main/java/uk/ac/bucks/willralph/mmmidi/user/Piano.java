package uk.ac.bucks.willralph.mmmidi.user;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import uk.ac.bucks.willralph.mmmidi.App;
import javax.sound.midi.Instrument;

import java.util.*;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import uk.ac.bucks.willralph.mmmidi.SoundFont;

public class Piano extends StackPane {
    public static final GridPane BLACK_NOTES = new GridPane();
    public static final GridPane WHITE_NOTES = new GridPane();
    public static final SoundFont sounds = new SoundFont();

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

        initScale();

        this.setBorder(blackBorder);
        System.out.println(this.getHeight()+" "+this.getWidth());

    }
    private static void scale() {
        System.out.println("We scaling");
        scaleChildNote(BLACK_NOTES.getChildren());
        scaleChildNote(WHITE_NOTES.getChildren());
    }
    private static void scaleChildNote(ObservableList<Node> children) {
        for(Node child : children) {
            if (!(child instanceof Note currentNote)) break;
            currentNote.stretch();
        }
    }
    private static boolean appWidthChanged = false;
    public static void setListeners() {
        App.mainStage.getScene().widthProperty().addListener(e -> {
            appWidthChanged = true;
        });
        NativeMouseInputListener mouseInputListener = new NativeMouseInputListener() {
            @Override
            public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
                NativeMouseInputListener.super.nativeMouseReleased(nativeEvent);
                System.out.println("Mouse released");
                if(appWidthChanged) scale();
                appWidthChanged = false;
            }
        };
        GlobalScreen.addNativeMouseListener(mouseInputListener);
    }
    private void initScale() {
        GridPane.setHgrow(WHITE_NOTES,Priority.ALWAYS);
        GridPane.setHgrow(BLACK_NOTES,Priority.ALWAYS);

        GridPane.setFillWidth(BLACK_NOTES,true);
        //HGrow causes pixel snapping

        WHITE_NOTES.setSnapToPixel(false);
        BLACK_NOTES.setSnapToPixel(false);
        //this.setSnapToPixel(false);

        BLACK_NOTES.setMaxHeight(60);
        BLACK_NOTES.setAlignment(Pos.TOP_CENTER);

        WHITE_NOTES.setAlignment(Pos.TOP_CENTER);

        // BLACK_NOTES.setGridLinesVisible(true); debug to see where the gaps are
        this.setAlignment(Pos.TOP_CENTER);

    }
    private int noteCount = 0;
    private static final int END_NOTES_OFFSET = 1;
    private void generateOctaves() {
        //makeBlackGap();
        int keyboardLength = getTotalWhiteCount();
        for (int i=0; i < keyboardLength; i++) {
            System.out.println(i);
            makeWhite(i);
            if(i+1==keyboardLength) endGap = true;
            makeBlack();
        }
        //makeBlackGap();
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
    private final static int GAP_BLACK_COUNT = 12;
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
        } else makeBlackGap();
    }

    public static int getTotalGapBlackCount() {
        return octaves * GAP_BLACK_COUNT + END_NOTES_OFFSET*2;
    }
    public static double getWhiteWidth() {
        return App.getSize().getWidth()/getTotalWhiteCount();
    }
    public static double getBlackWidth() {
        return App.getSize().getWidth()/(getTotalWhiteCount()*2);
    }
    private static int getTotalWhiteCount() {
        return octaves * OCTAVE + END_NOTES_OFFSET;
    }

    public void setOctaves(int octaves) {
        Piano.octaves = octaves;
    }
}
