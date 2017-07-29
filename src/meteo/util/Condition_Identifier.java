package meteo.util;

import meteo.weather.C_WeatherData;

public class Condition_Identifier {
    
    public Condition_Identifier(C_WeatherData input){
        this.CurrentWeather = input;
    }//Constructor
 
    // Time Identifier (True = Day , False = Night)
        public boolean isitDay(){
            boolean flag = false;
                String sTime = CurrentDateTime.GetTime();
                String[] Time = sTime.split(":");
                int hour = Integer.parseInt(Time[0]);
                if (isitSummer()||isitSpring()){
                    if (hour >= 6 && hour <= 20){
                        flag = true;
                    }
                }else if (isitWinter()||isitAutomn()){
                    if (hour >= 7 && hour <= 18){
                        flag = true;
                    }
                }
            return flag;
        }
    // ------------------
        
    // Season Identifier
        public boolean isitSummer(){
             boolean flag = false;
            String sDate = CurrentDateTime.GetDate();
            String[] sMonth = sDate.split("/");
            int Month = Integer.parseInt(sMonth[2]);
            if (Month >=7 && Month<=9){
                flag = true;
            }   
            return flag;
        }
        public boolean isitWinter(){
            boolean flag = false;
            String sDate = CurrentDateTime.GetDate();
            String[] sMonth = sDate.split("/");
            int Month = Integer.parseInt(sMonth[2]);
            if (Month >=1 && Month<=3){
                flag = true;
            }   
            return flag;
        }
        public boolean isitSpring(){
           boolean flag = false;
            String sDate = CurrentDateTime.GetDate();
            String[] sMonth = sDate.split("/");
            int Month = Integer.parseInt(sMonth[2]);
            if (Month >=4 && Month<=6){
                flag = true;
            }   
            return flag;
        }
        public boolean isitAutomn(){
             boolean flag = false;
            String sDate = CurrentDateTime.GetDate();
            String[] sMonth = sDate.split("/");
            int Month = Integer.parseInt(sMonth[2]);
            if (Month >=10 && Month<=12){
                flag = true;
            }   
            return flag;
        }
    // -----------------
    
    // Weather Identifier
        public boolean isitClear(){
            boolean flag = false;
                if (CurrentWeather.getWindSpeedKmh()<10 && CurrentWeather.getTempratureC()>=19){
                    flag = true;
                }
            return flag;
        }
        public boolean isitRainy(){
            boolean Flag = false;
            if (CurrentWeather.getRainIn()>10){
                Flag = true;
            }
            return Flag;
        }
        public boolean isitSunny(){
            boolean flag = false;
            if (CurrentWeather.getLight()>50){
                flag = true;
            }
            return flag; 
        }
        public boolean isitWindy(){
            boolean flag = false;
            if (CurrentWeather.getWindSpeedKmh() > 24){
                flag = true;
            }
            return flag;
        }
        public boolean isitSnowy(){
            boolean flag = false;
                if (CurrentWeather.getTempratureC()<-5 &&CurrentWeather.getRainIn()>20){
                    flag = true;
                }
            return flag;
        }
// -----------------
        public String BackgroundName(){
            String background_photo = this.Default;
                if (isitDay()){
                    // Sunny Day & Sunny windy day & sunny rainy day
                    if(isitSummer()||isitSpring() && isitSunny()){
                        if (isitWindy()){
                            background_photo = this.WindyDay;
                        }else{
                            background_photo = this.SunnyDay;
                        }
                    }else if (isitSummer()||isitSpring() && isitRainy()){
                        background_photo = this.RainyDay;
                    }

                    if (isitWinter()||isitAutomn() && isitSnowy()){
                        background_photo = this.SnowyDay;
                    }else if(isitWinter()||isitAutomn() && isitClear()){
                        background_photo = this.ClearNight;
                    }

                }else{
                    // Clear Summer Night
                    if (isitSummer()||isitSpring() && isitClear()){
                        background_photo = this.ClearNight;
                    }else if (isitSummer()||isitSpring() && isitRainy()){
                        background_photo = this.RainyNight;
                    }
                    
                    if (isitWinter()||isitAutomn() && isitClear()){
                        background_photo = this.ClearNight;
                    }else if(isitWinter()||isitAutomn() && isitRainy()){
                        background_photo = this.RainyNight;
                    }else if(isitWinter()||isitAutomn() && isitWindy()){
                        background_photo = this.WindyNight;
                    }
                }
            return background_photo;
        }

    // ==== Name of pictures ====
        private final String Default = "MeteoStationDefault.jpg";
        private final String SunnyDay = "Clear_Sunny_Day.png";
        private final String RainyDay = "Rainy_Weather.png";
        private final String WindyDay = "Windy_Clear.png";
        private final String SnowyDay = "Snowy_Day.png";
        private final String RainyNight = "Rainy_Night.png";
        private final String ClearNight = "Clear_Night.png";
        private final String WindyNight = "Windy_Clear_Night.png";
    // ===========================
        private String BackgroundName;
    // Weather Data
    private Date_Time CurrentDateTime = new Date_Time();
    private C_WeatherData CurrentWeather;
}