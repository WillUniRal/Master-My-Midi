package uk.ac.bucks.willralph.mmmidi;

import javafx.application.Platform;
import uk.ac.bucks.willralph.mmmidi.user.MidiCommandEvent;
import uk.ac.bucks.willralph.mmmidi.user.Note;
import uk.ac.bucks.willralph.mmmidi.user.Piano;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiReceiver implements Receiver {

    private final Receiver synthReceiver;
    MidiReceiver(Receiver receiver) {
        // requires receiver from the synth to make sound.
        synthReceiver = receiver;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        synthReceiver.send(message, timeStamp); // any logic must come after to decrease delay

        if(message instanceof ShortMessage shortMessage) {
            int command = shortMessage.getCommand(); //144 on 128 off
            int key = shortMessage.getData1();
            int velo = shortMessage.getData2();
            System.out.printf("%1$2s %2$2s %3$2s \n",command,key,velo);
            Note n = Piano.noteMap.get(key);
            if(n==null) return;
            Platform.runLater(new MidiCommandEvent(n,command));
        }
        System.out.println(message.toString());


    }

    @Override
    public void close() {
        synthReceiver.close();
    }
}
