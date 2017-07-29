package meteo.util;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;


public class Analytics {
    Timer UptimeCounter = new Timer();
    public void Start(){
        UptimeCounter.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Seconds++;
                        if(Seconds == 59){
                            Minutes++;
                            Seconds = 0;
                        }
                        if (Minutes == 59){
                            Hour++;
                            Minutes = 0;
                        }
                    }
                });
            }
        },0,1000);
    }
    
    public String GetUptime(){
        String shour = "";
        String sMinutes = "";
        String sSeconds = "";
        if (Hour < 10){
            shour = "0"+String.valueOf(Hour);
        }else{
            shour = String.valueOf(Hour);
        }
        if (Minutes < 10){
            sMinutes = "0"+String.valueOf(Minutes);
        }else{
            sMinutes = String.valueOf(Minutes);
        }
        if (Seconds < 10){
            sSeconds = "0"+String.valueOf(Seconds);
        }else{
            sSeconds = String.valueOf(Seconds);
        }
        return shour+":"+sMinutes+":"+sSeconds;
    }
    
    public void StopCounting(){
        UptimeCounter.cancel();
    }
    
    public boolean Check(){
        boolean Flag = false;
        if (Seconds>0 || Minutes>0 || Hour>0){
            Flag = true;
        }else{
            Flag = false;
        }
        return Flag;
    }
    private int Seconds;
    private int Minutes;
    private int Hour;
}
