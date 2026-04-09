package uk.ac.bucks.willralph.mmmidi.user;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class NoteQueue extends NoteAnim {

    private NoteAnim currentAnimNote = new NoteAnim(COLOUR);
    KeyValue height = new KeyValue(currentAnimNote.prefHeightProperty(),this.getHeight());
    KeyValue yPos = new KeyValue(currentAnimNote.translateYProperty(),this.getHeight());


    // this makes the falling notes
    NoteQueue(Type col) {
        super(col);
        System.out.println(col);
        this.getChildren().add(currentAnimNote);
    }
    @Override
    protected void initScale() {
        GridPane.setVgrow(this, Priority.ALWAYS);
        GridPane.setFillWidth(this,true);
        GridPane.setFillHeight(this,true);
        setAlignment(Pos.BOTTOM_CENTER);
    }
    private Timeline timeline;
    private final Duration ANIM_TIME = Duration.seconds(5);

    public void startAnim() {
        System.out.println("starting");

        KeyFrame frame = new KeyFrame(ANIM_TIME, height);

        timeline = new Timeline(frame);
        timeline.play();
    }
    public void stopAnim() {
        System.out.println("stoping");
        Duration currentRunTime = timeline.getCurrentTime();
        timeline.stop();
        KeyFrame frame = new KeyFrame(ANIM_TIME.subtract(currentRunTime), yPos);
        currentAnimNote.getPos();

        timeline = new Timeline(frame);
        timeline.setOnFinished(_ -> cleanUp(currentAnimNote));

        newAnimNote();
    }
    private void cleanUp(NoteAnim anim) {
        // Stuck at O(n) because javafx is -
        // O(1) with queue imp
        this.getChildren().remove(anim);
    }

    private void newAnimNote() {
        currentAnimNote = new NoteAnim(COLOUR);
    }
}
