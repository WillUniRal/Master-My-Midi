from rpi_ws281x import *
from abc import ABC, abstractmethod


class Note(ABC) :

    @property 
    @abstractmethod
    def size() : pass

    @property
    @abstractmethod
    def buffer() : pass
    
    START_VALUE = 36
    cur_val = 0

    @property
    def relative_pos(self) :
        return self.col_val*self.size

    def __init__(self,value,cls):
        self.col_val = cls.cur_val
        cls.cur_val += 1

        self.value = value
        self.setLEDs(25)
        
    def setLEDs(self,start_led) :
        self.position = start_led + self.relative_pos
        self.end = self.position + self.size
        # print(self.position,self.end, __class__)
    
    def setColor(self, color : Color) :
        self.color = color

    def turnOn(self,strip : Adafruit_NeoPixel,color : Color) :
        strip[self.position:self.end] = color * self.size

    def turnOff(self,strip : Adafruit_NeoPixel) :
        strip[self.position:self.end] = Color(0,0,0) * self.size



class White(Note) :

    size : int = 7
    buffer : dict[int,Note] = {}
    
    def __init__(self,value):
        super().__init__(value,White)

class Black(Note) :

    size : int = 3
    buffer : dict[int,Note]= {}

    def __init__(self,value):
        super().__init__(value,Black)

if __name__ == "__main__" :
    note = White(1)
    note2 = Black(2)
    note3 = White(3)
    print(note.col_val) # 1
    print(note2.col_val) # 1
    print(note3.col_val) # 2
