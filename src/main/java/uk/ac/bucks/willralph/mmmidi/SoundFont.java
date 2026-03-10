package uk.ac.bucks.willralph.mmmidi;

import uk.ac.bucks.willralph.mmmidi.user.Piano;

import javax.imageio.IIOException;
import javax.sound.midi.*;
import javax.sound.midi.spi.SoundbankReader;
import java.io.File;
import java.io.IOException;

public class SoundFont implements Soundbank {
    private final File PIANO_MED_C3 = new File("C3.wav");
    SoundFont() {
        Instrument piano = setSoundBank(PIANO_MED_C3).getInstrument()
    }
    private Soundbank setSoundBank(File file) {
        try {
            return MidiSystem.getSoundbank(file);
        } catch (IOException e) {
            throw new RuntimeException("Sound file");
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException("Something midi happen oops");
        }

    }

    @Override
    public String getName() {
        return "Grand Piano";
    }

    @Override
    public String getVersion() {
        return "";
    }

    @Override
    public String getVendor() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Classical sound of an acoustic grand piano";
    }

    @Override
    public SoundbankResource[] getResources() {
        return new SoundbankResource[0];
    }

    @Override
    public Instrument[] getInstruments() {
        return new Instrument[0];
    }

    @Override
    public Instrument getInstrument(Patch patch) {
        return null;
    }
}
