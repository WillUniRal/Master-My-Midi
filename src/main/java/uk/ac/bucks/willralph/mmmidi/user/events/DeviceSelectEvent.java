package uk.ac.bucks.willralph.mmmidi.user.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import uk.ac.bucks.willralph.mmmidi.MidiConnection;

import javax.sound.midi.MidiDevice.Info;
public class DeviceSelectEvent implements EventHandler<MouseEvent> {
    Info selectedDevice;
    public DeviceSelectEvent(Info info) {
        super();
        selectedDevice = info;
    }
    @Override
    public void handle(MouseEvent event) {
        System.out.println("handled");
        MidiConnection.openMidiConnection(selectedDevice);
    }
}
