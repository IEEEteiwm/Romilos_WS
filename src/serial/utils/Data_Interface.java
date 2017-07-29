/**
 * Title: Data_Interface
 * Type: Java Class
 * Description: This class is used as an interface between the class that controls 
 * Arduino via Universal Serial Bus and the application.
 */
package serial.utils;

import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import meteo.weather.C_WeatherData;

public class Data_Interface {
    
    /**
     * Name: Convert
     * Type: Method
     * Description: Converts the InputStream to String. Data from Arduino comes 
     * to this application as input stream. This method converts those data into
     * a string. The String seem like "$,,,,,#"
     * @param input
     * @return
     * @throws IOException 
     */
    public String Convert(InputStream input) throws IOException{
       return new BufferedReader(new InputStreamReader(input), 1).readLine();
    }
    
   /**
    * Name: GetWeather
    * Type: Method
    * Description: Sends Read signal to Arduino receivs Data and realize the 
    * convertion from String to String Array and finally to C_WeatherData Object.
    * @return
    * @throws Exception 
    */
    public C_WeatherData GetWeather()throws Exception{
       String DataFromSerial = "";
       C_WeatherData Object = null;
        Ard_conn.SendReadSignal();
        
        try{
           DataFromSerial = Convert(Ard_conn.ReadFromSerial());
        }catch(Exception e){
            e.printStackTrace();
        }
        
        ToHashMap(DataFromSerial);
        try{
            // Sets Data to Weather Class (change Float to Double)
            Object = new C_WeatherData(Integer.parseInt(hMap.get("WindDir")),
                                       Float.parseFloat(hMap.get("WindSpeedKmh")),
                                       Float.parseFloat(hMap.get("Humidity")),
                                       Double.parseDouble(hMap.get("TempratureC")),
                                       Double.parseDouble(hMap.get("RainIn")),
                                       Double.parseDouble(hMap.get("Pressure")),
                                       Double.parseDouble(hMap.get("Light")),
                                       Double.parseDouble(hMap.get("RealFeel")));
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        
        return Object;
    }
  
    public void ToHashMap(String Input){
        String[] DataSplitted = Input.split(",");
            try{
                hMap.put("StartSymbol",DataSplitted[0]);
                hMap.put("WindDir", DataSplitted[1]);
                hMap.put("WindSpeedKmh", DataSplitted[2]);
                hMap.put("Humidity", DataSplitted[3]);
                hMap.put("TempratureC", DataSplitted[4]);
                hMap.put("RainIn", DataSplitted[5]);
                hMap.put("Pressure", DataSplitted[6]);
                hMap.put("Light", DataSplitted[7]);
                hMap.put("RealFeel", DataSplitted[8]);
                hMap.put("StopSymbol",DataSplitted[9]);
            }catch(ArrayIndexOutOfBoundsException e){
            }
    }
    
    public boolean OpenConnection() throws UnsupportedCommOperationException{
       return Ard_conn.ConnectToBoard();
    }
    public boolean CloseConnection(){
        return Ard_conn.Disconect();
    }
    public void SetSelectedPort(String input){
        Ard_conn.SetPortAddress(input);
    }
    // ==== Class Fields
    private HashMap<String,String> hMap = new HashMap();
    private Arduino_Interface Ard_conn = new Arduino_Interface();
}
