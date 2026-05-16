import struct
from enum import IntEnum
from serial import Serial

from piano import Piano

ser: Serial = Serial('/dev/serial0', 115200, timeout=1)

header_len = 2

class Method(IntEnum) :
    ON = 0
    OFF = 1

piano = Piano() 
piano.make()

piano.strip.begin()


while True:
    
    if ser.in_waiting >= header_len:

        header = ser.read(header_len)
        method, msg_len = struct.unpack('B'*header_len, header)

        while ser.in_waiting < msg_len : pass
        data = ser.read(msg_len)

        if method == Method.ON :
            value, r, g, b = struct.unpack('B'*msg_len, data)
            piano.turnNoteOn(value, r,g,b)
        else :
            value = data[0]
            piano.turnNoteOff(value)
            #print(f"value :{value} (off)")

        piano.turnOnBuffers()
        
        piano.strip.show()

        
