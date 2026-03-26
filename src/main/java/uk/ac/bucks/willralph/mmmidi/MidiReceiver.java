package uk.ac.bucks.willralph.mmmidi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import java.util.Arrays;

public class MidiReceiver implements Receiver {

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage shortMessage) {
            int command = shortMessage.getCommand();
            int key = shortMessage.getData1();
            int velo = shortMessage.getData2();

            System.out.printf("%1$2s %2$2s %3$2s \n",command,key,velo);
        }
    }

    @Override
    public void close() {

    }
}
