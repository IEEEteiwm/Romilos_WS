package serial.utils;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Arduino_Interface {
    

    
    
    public boolean ConnectToBoard() throws UnsupportedCommOperationException{
        try{
         //Scans the ports and returns com3 as port 
        Port_Scanner = CommPortIdentifier.getPortIdentifier(Port_Address);
       // Opens the COM3 Port
        Com_port = Port_Scanner.open(this.getClass().getName(),TimeOut);
       // Casts the CommPortIdentifier to SerialPort
        Arduino_Port = (SerialPort) Com_port;
       // Disables the receive Timeout 
        Arduino_Port.disableReceiveTimeout();
       // and enables the receive threshold for receiving data every time.
        Arduino_Port.enableReceiveThreshold(1);
       // Sets parametres about speed bits and parity bits.
        Arduino_Port.setSerialPortParams(Baud_rate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        }catch(NoSuchPortException e){
            Alert alert = new Alert(AlertType.ERROR, "Δεν βρέθηκε συμβατή  η συνδεδεμένη συσκευή στην θύρα που επιλέξατε. Για τον εντοπισμό της θύρας οπού η συσκευή σας επικοινωνεί με τον υπολογιστή διαβάστε τις οδηγίες στο πεδίο βοήθεια (Κωδικός Σφάλματος: 0126)", ButtonType.OK);
            alert.showAndWait();
        }catch(PortInUseException e){
            Disconect();
            Alert alert = new Alert(AlertType.ERROR, "Η θύρα που επιλέξατε χρησιμοποιείται ήδη από κάποια άλλη εφαρμογή, για περισσότερες πληροφορίες ανατρέξτε στην βοήθεια. (Κωδικός Σφάλματος: 0125)", ButtonType.OK);
            alert.showAndWait();
        }
        return true;
    }
   /**
    * Returns Data from Serial Port.
    * @return
    * @throws Exception 
    */
    public InputStream ReadFromSerial() throws Exception {
        in = Arduino_Port.getInputStream();
        return in;
    }
    
   /**
    * Sends the string n to Arduino for the "Interrupt" 
    * @return
    * @throws Exception 
    */
    public boolean SendReadSignal() throws Exception {
        out = Arduino_Port.getOutputStream();
        out.write("n".getBytes());
        return true;
    }
    
    public boolean Wait_Hight_Priority(){
        try{
            in = Arduino_Port.getInputStream();
            if (in.equals("Hight")){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Send_Medium_Priority(){
            try{
                out = Arduino_Port.getOutputStream();
                out.write("m".getBytes());
            }catch(Exception e){
                e.printStackTrace();
            }
        return true;
    }
    
    public boolean Send_low_Priority(){
            try{
                out = Arduino_Port.getOutputStream();
                out.write("ee".getBytes());
            }catch(Exception e){
                e.printStackTrace();
            }
        return true;
    }

    /**
     * Sets Selected Port to class
     * @param Input 
     */
    public void SetPortAddress(String Input){
        this.Port_Address = Input;
    }
    public boolean Disconect(){
          try{
            Arduino_Port.close();
            in.close();
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    // --- Declaration
    private String Data;
    private InputStream in;
    private OutputStream out;
    // == Serial Port Declaration
    private CommPort Com_port;
    private CommPortIdentifier Port_Scanner;
    private SerialPort Arduino_Port; // Serial Port Object
    private final int TimeOut = 2000; // 3 Seconds for connection  
    private final int Baud_rate = 9600; // Connection Speed
    private String Port_Address;
}
