import mido 
from tkinter import *

class window: 
    def __init__(self,x,y):
        self.width = x
        self.height = y
    
    @Variable
    def size(self):
        
        return "Current screen resolution: Width=",self.width


        
