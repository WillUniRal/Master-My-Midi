package uk.ac.bucks.willralph.mmmidi;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundFont implements Soundbank {
    private final Instrument[] INSTRUMENTS;

    public SoundFont() {
        URL resource = getClass().getClassLoader().getResource("PianoSoundFont.sf2");
        INSTRUMENTS = setSoundBank(resource).getInstruments();
        for( Instrument instrument : INSTRUMENTS ) {
            System.out.println("lololol"+instrument.getPatch().toString());
        }

    }
    private Soundbank setSoundBank(URL resource) {
        try {
            return MidiSystem.getSoundbank(resource);
        } catch (IOException e) {
            throw new RuntimeException("Sound file");
        } catch (InvalidMidiDataException e) {
            System.err.println(e.getMessage()+" boop "+e.getLocalizedMessage());
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
        return INSTRUMENTS;
    }

    @Override
    public Instrument getInstrument(Patch patch) {
        return null;
    }
}
