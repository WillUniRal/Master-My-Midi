package uk.ac.bucks.willralph.mmmidi.user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import uk.ac.bucks.willralph.mmmidi.MidiConnection;

import javax.sound.midi.MidiDevice.Info;
public class DeviceSelectEvent implements EventHandler<MouseEvent> {
    Info selectedDevice;
    DeviceSelectEvent(Info info) {
        super();
        selectedDevice = info;
    }
    @Override
    public void handle(MouseEvent event) {
        System.out.println("handled");
        MidiConnection.openMidiConnection(selectedDevice);
    }
}
