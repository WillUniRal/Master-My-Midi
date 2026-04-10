package uk.ac.bucks.willralph.mmmidi.user;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.Queue;

public class NoteQueue extends NoteAnim {

    private NoteAnim currentAnimNote = new NoteAnim(COLOUR);
    private KeyValue height;
    private KeyValue yPos;


    // this makes the falling notes
    NoteQueue(Type col) {
        super(col);
    }
    @Override
    protected void callStyle(){} // do nothing
    @Override
    protected void initScale() {
        GridPane.setVgrow(this, Priority.ALWAYS);
        GridPane.setFillWidth(this,true);
        GridPane.setFillHeight(this,true);
        setAlignment(Pos.BOTTOM_CENTER);
    }
    private void makeKeyFrames() {
        System.out.println(this.getHeight());
        height = new KeyValue(currentAnimNote.minHeightProperty(),this.getHeight());
        yPos = new KeyValue(currentAnimNote.layoutYProperty(),this.getHeight());
    }
    private Timeline timeline;
    private final Duration ANIM_TIME = Duration.seconds(5);

    public void startAnim() {
        makeKeyFrames();

        anims.add(currentAnimNote);

        System.out.println("starting");
        this.getChildren().add(currentAnimNote);
        KeyFrame frame = new KeyFrame(ANIM_TIME, height);

        timeline = new Timeline(frame);
        timeline.play();
    }
    public void stopAnim() {
        System.out.println("stoping");
        newAnimNote();

        Duration currentRunTime = timeline.getCurrentTime();
        System.out.println(currentRunTime);
        timeline.stop();
        //.subtract(currentRunTime)
        KeyFrame frame = new KeyFrame(ANIM_TIME, yPos);

        timeline = new Timeline(frame);
        timeline.playFromStart();
        timeline.setOnFinished(_ -> cleanUp());


    }
    private final Queue<NoteAnim> anims = new ArrayDeque<>();
    private void cleanUp() {
        // Stuck at O(n) because javafx is -
        // O(1) with queue imp
        System.out.println("clean");
        this.getChildren().remove(anims.poll());
    }

    private void newAnimNote() {
        currentAnimNote = new NoteAnim(COLOUR);
    }
}
