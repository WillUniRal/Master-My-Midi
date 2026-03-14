package uk.ac.bucks.willralph.mmmidi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;

public class MidiConnection {

    static {
        midiDevices = MidiSystem.getMidiDeviceInfo();
        inputDevices = getAllAvailableDevices();
    }

    private static final Info[] midiDevices;
    public static ArrayList<Info> inputDevices;

    private static ArrayList<Info> getAllAvailableDevices() {
        int devicesCount = 0;
        ArrayList<Info> result = new ArrayList<Info>();
        for(Info info : midiDevices) {
            MidiDevice device;
            try {device = MidiSystem.getMidiDevice(info); }
            catch (MidiUnavailableException e)
            {System.err.println(e.getMessage()); continue;}

            if(device.isOpen()) System.out.println("Open");
            if(device.getMaxTransmitters() == 0) continue; // Must be an output device
            
            devicesCount++;
            result.add(info);
            System.out.println("DEVICE "+devicesCount+":");
            System.out.println(info.getName());
            System.out.println(info.getDescription());

        }
        return result;
    }
}
