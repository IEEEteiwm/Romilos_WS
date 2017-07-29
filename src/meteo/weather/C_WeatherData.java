
package meteo.weather;

public class C_WeatherData {
     public C_WeatherData(int WindDirection, 
                          float WindSpeedKmh,
                          float Humidity,
                          double TempratureC,
                          double RainIn,
                          double Pressure,
                          double Light,
                          double RealFeelC) {
        this.WindDirection_value = WindDirection;
        this.WindSpeedKmh = WindSpeedKmh;
        this.Humidity = Humidity;
        this.TempratureC = TempratureC;
        this.RainIn = RainIn;
        this.Pressure = Pressure;
        this.Light = Light;
        this.RealFeelC = RealFeelC;
        Detect_Direction();
    }

     /**
     * Set Methods
     * @return 
     */
    public void setWindDirection(int WindDirection) {
        this.WindDirection_value = WindDirection;
    }
    public void setWindSpeedKmh(float WindSpeedKmh) {
        this.WindSpeedKmh = WindSpeedKmh;
    }
    public void setHumidity(float Humidity) {
        this.Humidity = Humidity;
    }
    public void setTempratureC(double TempratureC) {
        this.TempratureC = TempratureC;
    }
    public void setRainIn(double RainIn) {
        this.RainIn = RainIn;
    }
    public void setPressure(double Pressure) {
        this.Pressure = Pressure;
    }
    public void setLight(double Light) {
        this.Light = Light;
    }

    public void setRealFeelC(double RealFeelC) {
        this.RealFeelC = RealFeelC;
    }

    /**
     * Get Methods
     * @return 
     */
    
    public int getWindDirection() {
        return WindDirection_value;
    }
    public float getWindSpeedKmh() {
        return WindSpeedKmh;
    }
    public float getHumidity() {
        return Humidity;
    }
    public double getTempratureC() {
        return TempratureC;
    }
    public double getRainIn() {
        return RainIn;
    }
    public double getPressure() {
        return Pressure;
    }
    public double getLight() {
        return Light;
    }

    public double getRealFeelC() {
        return RealFeelC;
    }
    public String get_Direction_String(){
        return WindDirection_String;
    }
    
    public String Detect_Direction(){
        
        if (WindDirection_value>0 &&WindDirection_value<90){
            WindDirection_String = "Νοτιοδυτική";
        }else if(WindDirection_value == 90){
            WindDirection_String = "Δυτική";
        }else if(WindDirection_value == 0){
            WindDirection_String = "Νότια";
        }else if(WindDirection_value == 180){
            WindDirection_String = "Βόρεια";
        }else if(WindDirection_value == 270){
            WindDirection_String = "Ανατολική";
        }else if(WindDirection_value>90 &&WindDirection_value<180){
            WindDirection_String = "Βορειοδυτική";
        }else if(WindDirection_value>180 &&WindDirection_value<270){
            WindDirection_String = "Βορειοανατολική";
        }
        else
        {
            WindDirection_String = "Νοτιοανατολική";
        }
        
        return WindDirection_String;
    }

    // ==== Declaration of Fields
    private int WindDirection_value;
    private String WindDirection_String;
    private float WindSpeedKmh;
    private float Humidity;
    private double TempratureC;
    private double RealFeelC;
    private double RainIn;
    private double Pressure;
    private double Light;
}
