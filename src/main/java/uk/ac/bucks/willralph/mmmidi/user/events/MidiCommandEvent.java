package uk.ac.bucks.willralph.mmmidi.user.events;

import uk.ac.bucks.willralph.mmmidi.user.nodes.Note;

public class MidiCommandEvent implements Runnable {
    private final Note note;
    private final int command;

    public MidiCommandEvent(Note note, int command) {
        this.note = note;
        this.command = command;
    }
    @Override
    public void run() {
        switch (command) {
            case 144 -> note.pressed();
            case 128 -> note.unpressed();
        }
    }
}
