from rpi_ws281x import *
from abc import ABC, abstractmethod


class Note(ABC) :

    START_VALUE = 36
    cur_count = 0

    @property 
    @abstractmethod
    def size() : pass

    @property
    @abstractmethod
    def buffer() : pass

    @property
    @abstractmethod
    def relative_pos() : pass

    def __init__(self, value, cls) :
        # col value is how many 
        self.color_count = cls.cur_count
        cls.cur_count += 1

        self.value = value
        self.setLEDs(10)
        
    def setLEDs(self, start_led) :
        self.position = start_led + self.relative_pos
        self.end = self.position + self.size
    
    def setColor(self, color : Color) :
        self.color = color

    def turnOn(self, strip : Adafruit_NeoPixel) :
        strip[self.position:self.end] = self.color * self.size

    def turnOff(self, strip : Adafruit_NeoPixel) :
        strip[self.position:self.end] = Color(0,0,0) * self.size



class White(Note) :

    size : int = 7
    buffer : dict[int, Note] = {}
    
    def __init__(self, value) :
        super().__init__(value, White)

    @property
    def relative_pos(self) :
        return self.color_count*self.size

    

class Black(Note) :

    size : int = 3
    buffer : dict[int, Note]= {}
    total_size : int = 0

    def __init__(self, value) :
        Black.total_size += 5
        self.tail_size = Black.total_size

        super().__init__(value, Black)
        self.set_relative_pos()
        
        
        
    @property
    def relative_pos(self) :
        return self.tail_size

    def set_relative_pos(self) :
         
        if self.SHARP_NOTES[self.color_count % 5]() and self.color_count!=0 : 
            Black.total_size += 5
        
        Black.total_size += self.size
    
    @property
    def SHARP_NOTES(self) -> dict[int,callable]:
        return {
            0 : self.B,
            1 : self.C,
            2 : self.E,
            3 : self.F,
            4 : self.G
        }
    

    def B(_) : return False
    def C(_) : return True
    def E(_) : return False
    def F(_) : return False
    def G(_) : return True
      
if __name__ == "__main__" :
    note = White(1)
    note2 = Black(2)
    note3 = White(3)
    print(note.color_count) # 1
    print(note2.color_count) # 1
    print(note3.color_count) # 2
