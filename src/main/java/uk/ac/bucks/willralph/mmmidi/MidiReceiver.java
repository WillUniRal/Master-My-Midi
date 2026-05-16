package uk.ac.bucks.willralph.mmmidi;

import javafx.application.Platform;
import uk.ac.bucks.willralph.mmmidi.user.events.MidiCommandEvent;
import uk.ac.bucks.willralph.mmmidi.user.nodes.Note;
import uk.ac.bucks.willralph.mmmidi.user.nodes.Piano;

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
            //System.out.printf("%1$2s %2$2s %3$2s \n",command,key,velo);
            Note n = Piano.noteMap.get(key);
            if(n==null) return;
            Platform.runLater(new MidiCommandEvent(n,command));
        }
        //System.out.println(message.toString());
        //128 47  0
        //com.sun.media.sound.FastShortMessage@4db7f8a3
    }

    @Override
    public void close() {
        synthReceiver.close();
    }
}
