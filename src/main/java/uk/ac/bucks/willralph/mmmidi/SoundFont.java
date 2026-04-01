package uk.ac.bucks.willralph.mmmidi;

import javax.sound.midi.*;
import java.io.IOException;
import java.net.URL;

public class SoundFont implements Soundbank {
    private final Instrument[] INSTRUMENTS;
    private final Soundbank BANK;
    private static final Synthesizer SYNTH;

    static {
        SYNTH = setSynthesizer();
    }

    public static MidiReceiver synthReceiver(){
        try {
            return new MidiReceiver(SYNTH.getReceiver());
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public Instrument currentInstrument;
    public SoundFont() {
        URL resource = getClass().getClassLoader().getResource("PianoSoundFont.sf2");
        BANK = setSoundBank(resource);
        INSTRUMENTS = BANK.getInstruments();

        initCurrentInstrument();
    }
    private void initCurrentInstrument() {
        System.out.println("All available instruments");
        for( Instrument instrument : INSTRUMENTS ) {
            System.out.println(instrument.getPatch().toString());
        }
        currentInstrument = INSTRUMENTS[0];
        SYNTH.loadInstrument(currentInstrument);
        channels = SYNTH.getChannels();

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
    private static Synthesizer setSynthesizer() {
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            return synth;
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    private MidiChannel[] channels;

    public void playNote(int noteNumber, int velo, int channelIndex) {
        // sustain pedal, not working for my custom sound font
        // channels[channelIndex].controlChange(64, 127);
        channels[channelIndex].noteOn(noteNumber,velo);
    }

    @Override
    public String getName() {
        return BANK.getName();
    }

    @Override
    public String getVersion() {
        return BANK.getVersion();
    }

    @Override
    public String getVendor() {
        return BANK.getVendor();
    }

    @Override
    public String getDescription() {
        return "Classical sound of an acoustic grand piano";
    }

    @Override
    public SoundbankResource[] getResources() {
        return BANK.getResources();
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
