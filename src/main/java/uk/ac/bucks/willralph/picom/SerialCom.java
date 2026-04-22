package uk.ac.bucks.willralph.picom;
import com.fazecast.jSerialComm.SerialPort;

public class SerialCom {

        private final SerialPort comPort;
        private boolean connection;

        public SerialCom() {
            comPort = getComPort();
            confComPort();
            connection = openComPort();
        }

        private SerialPort getComPort() {
            return SerialPort.getCommPort("ttyUSB0");
        }

        private void confComPort() {
            comPort.setBaudRate(115200);
            comPort.setNumDataBits(8);
            comPort.setNumStopBits(1);
            comPort.setParity(SerialPort.NO_PARITY);
        }

        private boolean openComPort() {
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

        public void sendData(int value) {
            if(!connection) {
                //reattempt
                if(!openComPort()) return;
                connection = true;
            }
            String data = "Value " + value;
            byte[] buffer = data.getBytes();
            comPort.writeBytes(buffer, buffer.length);

        }

        public void closeCom() {
            comPort.closePort();
        }

}
