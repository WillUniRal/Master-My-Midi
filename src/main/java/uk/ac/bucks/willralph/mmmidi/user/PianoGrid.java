package uk.ac.bucks.willralph.mmmidi.user;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import uk.ac.bucks.willralph.mmmidi.App;

public abstract class PianoGrid extends StackPane {

    public static final GridPane BLACK_NOTES = new GridPane(); //m
    public static final GridPane WHITE_NOTES = new GridPane(); //m

    protected static int octaves =5; //m
    public final static int OCTAVE=7; // repeats at 8

    private static void scale() {
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
                if(appWidthChanged) scale();
                appWidthChanged = false;
            }
        };
        GlobalScreen.addNativeMouseListener(mouseInputListener);
    }

    private static final int END_NOTES_OFFSET = 1;
    public double getWhiteWidth() {
        return App.getSize().getWidth()/getTotalWhiteCount();
    }
    public double getBlackWidth() {
        return App.getSize().getWidth()/(getTotalWhiteCount()*2);
    }
    protected int getTotalWhiteCount() {
        return octaves * OCTAVE + END_NOTES_OFFSET;
    }

    protected abstract void makeBlackGap(int... column);

    protected abstract void makeWhite(int... column);

    protected abstract void makeBlack(int... column);

}
