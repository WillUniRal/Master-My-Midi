package uk.ac.bucks.willralph.mmmidi.user;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

import uk.ac.bucks.willralph.mmmidi.SoundFont;
import uk.ac.bucks.willralph.mmmidi.MidiConnection;

public class Piano extends PianoGrid {
    public static final SoundFont sounds = new SoundFont(); //k
    private static final MidiConnection midiDevice = new MidiConnection(); //k
    public final PianoVisualizer VISUALIZER = new PianoVisualizer();

    private final Border blackBorder = new Border ( new BorderStroke(
          Color.BLACK,
          BorderStrokeStyle.SOLID,
          CornerRadii.EMPTY,
          BorderStroke.THIN
    ));

    private static final Integer[] GAP_VALUES = new Integer[] {0,2,4,5,6,8,10,12,13}; //k
    private final Set<Integer> BLACK_GAPS = new HashSet<>(Arrays.asList(GAP_VALUES)); //k

    private boolean endGap = false;
    private boolean isGap() {
        int DIV_COUNT = 14;
        boolean result = BLACK_GAPS.contains(noteCount % DIV_COUNT) || endGap;
        endGap = false;
        return result;
    }
    Piano(int oct) {
        super();

        octaves = oct;

        this.setWidth(Double.MAX_VALUE);
        this.setMinHeight(100);
        this.setHeight(100);

        if(!initialized) generateOctaves();

        this.getChildren().add(WHITE_NOTES);
        this.getChildren().add(BLACK_NOTES);

        initScale();

        this.setBorder(blackBorder);
        System.out.println(this.getHeight()+" "+this.getWidth());

    }
    public static HashMap<Integer, Note> noteMap = new HashMap<>();
    public static void tune(Note note, int... start){
        if (start.length>1) {
            note.beginCount(start[0]);
            noteMap.clear();
        }
        note.noteCount();
        addNote(note);
    }
    //k
    private static void addNote(Note note) {
        noteMap.put(note.getValue(),note);
    }

    private void initScale() {

        GridPane.setFillWidth(BLACK_NOTES,true);
        //enabling HGrow will cause pixel snapping
        WHITE_NOTES.setSnapToPixel(false);
        BLACK_NOTES.setSnapToPixel(false);

        BLACK_NOTES.setMaxHeight(60);

        BLACK_NOTES.setAlignment(Pos.TOP_CENTER);
        WHITE_NOTES.setAlignment(Pos.TOP_CENTER);

        this.setAlignment(Pos.TOP_CENTER);
        // BLACK_NOTES.setGridLinesVisible(true); debug to see where the gaps are
    }
    // will generate from this class to construct the visualizer
    private int noteCount = 0;
    private static boolean initialized = false;
    //k
    private void generateOctaves() {
        initialized = true;
        //makeBlackGap();
        int keyboardLength = getTotalWhiteCount();
        for (int i=0; i < keyboardLength; i++) {
            makeWhite(i);
            if(i+1==keyboardLength) endGap = true;
            makeBlack();
        }
        //makeBlackGap();
    }
    @Override
    protected void makeBlackGap(int... column) {
        Note.Type type = Note.Type.GAP;
        BLACK_NOTES.addColumn(noteCount,new Note(type,this));
        //VISUALIZER.setCurrentNote(noteCount, new NoteQueue(type));
    }
    @Override
    protected void makeWhite(int... column) {
        Note.Type type = Note.Type.WHITE;
        noteCount++;
        makeBlackGap(); // for every white key there is a black gap

        Note newNote = new Note(type,this);

        WHITE_NOTES.addColumn(column[0],newNote);
        //VISUALIZER.setCurrentNote(column[0],new NoteQueue(type));

        addNote(newNote);
    }
    private final static int GAP_BLACK_COUNT = 12;
    @Override
    protected void makeBlack(int... column) {
        boolean make = !isGap();
        Note.Type type = Note.Type.BLACK;
        //if (Note.total == (octaves*DIV_COUNT)-1) return;
        noteCount++;
        if(make) {
            Note newNote = new Note(type,this);
            BLACK_NOTES.addColumn(noteCount,newNote);
            //VISUALIZER.setCurrentNote(noteCount,new NoteQueue(type));
            addNote(newNote);
        } else makeBlackGap();
    }
    // this is needed for scaling in the Notes
    private static final int END_NOTES_OFFSET = 1;
    public static int getTotalGapBlackCount() {
        return octaves * GAP_BLACK_COUNT + END_NOTES_OFFSET*2;
    }
    public void setOctaves(int octaves) {
        Piano.octaves = octaves;
    }
}
