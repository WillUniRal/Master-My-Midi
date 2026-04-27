package uk.ac.bucks.willralph.picom;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;

import java.io.IOException;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SerialCom {

        private final SerialPort comPort;
        private boolean connection;

        public SerialCom() {
            comPort = getComPort();
            confComPort();
            connection = openComPort();
        }

        private SerialPort getComPort() {
            for(SerialPort port : SerialPort.getCommPorts()) {
                if (port.getSystemPortName().contains("USB")) return port;
            }
            // SerialPortInvalidPortException
            return null;
        }

        private void confComPort() {
            if(comPort == null) return;
            comPort.setBaudRate(115200);
            comPort.setNumDataBits(8);
            comPort.setNumStopBits(1);
            comPort.setParity(SerialPort.NO_PARITY);
        }

        private boolean openComPort() {
            if (comPort == null) return false;
            boolean result = comPort.openPort();
            if (result)  System.out.println("Port open successfully.");
            else comPortFail();
            return result;
        }
        private void comPortFail() {
            System.err.println("Failed to open port. Check permissions/connection.");
            System.err.println("Err Code: "+comPort.getLastErrorCode());
            // 16 - Busy (Solution: kill any processes e.g. screen)
        }
        public enum Method {
            ON,
            OFF
        }
        public void sendData(Method method, int value, Color ...colors) {
            if(!connection) {
                //reattempt
                if(!openComPort()) return;
                connection = true;
            }
            int length;
            System.out.println(colors.length*3);
            length = 1+(colors.length*3);

            ByteBuffer byteBuffer = ByteBuffer.allocate(2+length);

            byteBuffer.put((byte) method.ordinal());
            byteBuffer.put((byte) length);
            byteBuffer.put((byte) value);

            for (Color color : colors) {
                byteBuffer.put((byte) color.getRed());
                byteBuffer.put((byte) color.getGreen());
                byteBuffer.put((byte) color.getBlue());
            }
            
            //System.out.println(Arrays.toString(byteBuffer) +"bval ");
            comPort.writeBytes(byteBuffer.array(), byteBuffer.capacity());

        }

        public void closeCom() {
            comPort.closePort();
        }

}
