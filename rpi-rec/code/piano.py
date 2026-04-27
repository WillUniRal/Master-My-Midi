from note import *

# LED strip configuration:
LED_COUNT      = 332     # Number of LED pixels.
LED_PIN        = 12      # GPIO pin connected to the pixels (18 uses PWM!).
#LED_PIN        = 10      # GPIO pin connected to the pixels (10 uses SPI /dev/spidev0.0).
LED_FREQ_HZ    = 800000  # LED signal frequency in hertz (usually 800khz)
LED_DMA        = 10      # DMA channel to use for generating a signal (try 10)
LED_BRIGHTNESS = 15      # Set to 0 for darkest and 255 for brightest
LED_INVERT     = False   # True to invert the signal (when using NPN transistor level shift)
LED_CHANNEL    = 0       # set to '1' for GPIOs 13, 19, 41, 45 or 53

class Piano :

    OCTAVE_SIZE = 12
    END_NOTES_OFFSET = 1

    white_note_buffer : dict[int,White]= {}
    black_note_buffer : dict[int,Black]= {}

    def __init__(self,octaves=5):
        self.octaves = octaves
        self.offset = 0

        self.strip = Adafruit_NeoPixel(
            LED_COUNT,
            LED_PIN,
            LED_FREQ_HZ, 
            LED_DMA, 
            LED_INVERT, 
            LED_BRIGHTNESS, 
            LED_CHANNEL
        )

    @property
    def __piano_len(self) :
        #returns how many white keys
        return self.OCTAVE_SIZE*self.octaves+self.END_NOTES_OFFSET
    
    @property
    def __piano_range(self) :
        return range(Note.START_VALUE,Note.START_VALUE+self.__piano_len)
    
    
    @property
    def is_white(self) -> bool : 
        E : bool = (0 == (self.current_note - 5) % self.OCTAVE_SIZE)
        B : bool = (0 == self.current_note % self.OCTAVE_SIZE)
        # if the previous note was black make it white
        # if is e or b make it white next
        self.__white = self.__white == (E or B)
        return self.__white
    
    def turnNoteOn(self,value, *args) :
        on_note : Note = self.notes[value]
        on_note.setColor(Color(*args))
        
        on_note.buffer[value] = on_note
        

    def turnNoteOff(self,value) :
        self.notes[value].turnOff(self.strip)

    def make(self) :
        self.notes : dict[int,Note] = {}
        self.__white = True

        self.current_note = Note.START_VALUE
        for i in self.__piano_range :
            self.current_note = i
            self.notes[i] = White(i) if self.is_white else Black(i)

            
    
if __name__ == "__main__" :
    test = Piano() 
    test.make()
    # success
    test.turnNoteOn(36,255,0,0)
    test.turnNoteOn(37,0,0,255)

            


