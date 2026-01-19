package uk.ac.bucks.willralph.mmmidi.app;

public class Main {
    public static void main(String[] args) {
        App midiApp = new App();
        try {
            midiApp.init();
            midiApp.start(midiApp.mainStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
