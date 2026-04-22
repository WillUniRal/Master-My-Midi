package uk.ac.bucks.willralph.mmmidi.user;

import com.fazecast.jSerialComm.SerialPort;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import javafx.scene.layout.*;

import java.util.*;

import uk.ac.bucks.willralph.mmmidi.App;
import uk.ac.bucks.willralph.mmmidi.SoundFont;
import uk.ac.bucks.willralph.mmmidi.MidiConnection;
import uk.ac.bucks.willralph.picom.SerialCom;

public class Piano extends PianoGrid {
    public final SerialCom SERIAL = new SerialCom();
    public static final SoundFont sounds = new SoundFont(); //k
    private static final MidiConnection midiDevice = new MidiConnection(); //k
    public final PianoVisualizer VISUALIZER = new PianoVisualizer();

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
        octaves = oct;

        if(!initialized) generateOctaves();
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
    private static void addNote(Note note) {
        noteMap.put(note.getValue(),note);
    }
    @Override
    protected void initScale() {
        super.initScale();
        VISUALIZER.initScale();
        offsetBlack();
        //enabling HGrow will cause pixel snapping
        //BLACK_NOTES.setGridLinesVisible(true); //debug to see where the gaps are
        this.setWidth(Double.MAX_VALUE);
        this.setMinHeight(100);
        this.setHeight(100);
        GridPane.setFillWidth(BLACK_NOTES,true);
        BLACK_NOTES.setMaxHeight(60);

    }
    // will generate from this class to construct the visualizer
    private int noteCount = 0;
    private static boolean initialized = false;
    //k
    private void generateOctaves() {
        initialized = true;
        int keyboardLength = getTotalWhiteCount();
        for (int i=0; i < keyboardLength; i++) {
            makeWhite(i);
            if(i+1==keyboardLength) endGap = true;
            makeBlack();
        }
    }
    @Override
    protected void makeBlackGap(int... column) {
        Note.Type type = Note.Type.GAP;
        BLACK_NOTES.addColumn(noteCount,new Note(type,this,makeQueue(noteCount,type)));
    }
    private NoteQueue makeQueue(int column, Note.Type type) {
        NoteQueue q = new NoteQueue(type);

        VISUALIZER.setCurrentNote(column,q);
        return q;
    }
    @Override
    protected void makeWhite(int... column) {
        Note.Type type = Note.Type.WHITE;
        noteCount++;
        makeBlackGap(); // for every white key there is a black gap

        Note newNote = new Note(type,this,makeQueue(column[0],type));
        WHITE_NOTES.addColumn(column[0],newNote);

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
            Note newNote = new Note(type,this, makeQueue(noteCount,type));
            BLACK_NOTES.addColumn(noteCount,newNote);

            addNote(newNote);
        } else makeBlackGap();
    }
    private static boolean appWidthChanged = false;
    public void setListeners() {
        App.mainStage.getScene().widthProperty().addListener(e -> {
            appWidthChanged = true;
        });
        NativeMouseInputListener mouseInputListener = new NativeMouseInputListener() {
            @Override
            public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
                NativeMouseInputListener.super.nativeMouseReleased(nativeEvent);
                offsetBlack();
                if(appWidthChanged) scale();
                appWidthChanged = false;
            }
        };
        GlobalScreen.addNativeMouseListener(mouseInputListener);
    }
    private void offsetBlack() {
        VISUALIZER.BLACK_NOTES.setTranslateX(getBlackWidth()/2);
        BLACK_NOTES.setTranslateX(getBlackWidth()/2);
    }
    public void setOctaves(int octaves) {
        Piano.octaves = octaves;
    }
}
