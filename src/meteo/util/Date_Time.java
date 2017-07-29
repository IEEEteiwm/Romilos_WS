package meteo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Time {
    
    public String GetTime(){
        DateFormat CustomFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return CustomFormat.format(date);
    }
    public String GetDate(){
        DateFormat CustomFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return CustomFormat.format(date);
    }
    
    public String GetDateTime(){
        DateFormat CustomFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        Date date = new Date();
        return CustomFormat.format(date);
    }
}
