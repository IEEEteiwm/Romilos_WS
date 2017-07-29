/**
 * Title: StartScreenController
 * Type: Java Class
 * Description: This class is linked with StartScreen.fxml file in
 * 'graphics.windows' package. This class is the first class that runs when 
 * the program starts and it makes the program functional.
 */
package graphics.controllers;

import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import meteo.util.Date_Time;
import meteo.util.Analytics;
import meteo.util.Condition_Identifier;
import meteo.weather.C_WeatherData;
import serial.utils.Data_Interface;

public class StartScreenController implements Initializable {
   // === FXML Components Decleration ==== 
        private Pane ieee_logo;
        @FXML
        private Pane background;
        @FXML
        private MenuItem mnu_connect;
        @FXML
        private MenuItem mnu_Disconnect;
        @FXML
        private MenuItem mnu_Exit;
        @FXML
        private ToggleGroup PortGroup;
        @FXML
        private RadioMenuItem PortCOM1;
        @FXML
        private RadioMenuItem PortCOM2;
        @FXML
        private RadioMenuItem PortCOM3;
        @FXML
        private RadioMenuItem PortCOM4;
        @FXML
        private RadioMenuItem PortCOM5;
        @FXML
        private RadioMenuItem PortCOM6;
        @FXML
        private RadioMenuItem PortCOM7;
        @FXML
        private Label lbl_temperature;
        @FXML
        private Label lbl_app_title;
        @FXML
        private Label lbl_more;
        @FXML
        private Label lbl_humidity_txt;
        @FXML
        private Label lbl_rainin_txt;
        @FXML
        private Label lbl_windspeed_txt;
        @FXML
        private Label lbl_winddir_txt;
        @FXML
        private Label lbl_lightlvl_txt;
        @FXML
        private Label lbl_presoure_txt;
        @FXML
        private Line shape_line;
        @FXML
        private Label lbl_time;
        @FXML
        private Label lbl_date;
        @FXML
        private Label lbl_RealFeel;
        @FXML
        private MenuItem mnu_About;
        @FXML
        private Menu mnu_Help;
    // ==== END OF FXML COMPONENTS ===== 
    @FXML
    private MenuItem btnHelp;

    /**
     * Initializes the controller class.
     * Starts the clock.
     * Starts the Analytics
     * Disables the disconnect button
     * sets the default background and logo 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeProvider();
        ClearScreen();
        mnu_Disconnect.setDisable(true);
        ChangeBackground("MeteoStationDefault.jpg"); 
    }  
    
    // ==== FXML COMPONENTS ACTION ============
        @FXML
        private void COM1_Selected(ActionEvent event) {
            SelectedPort = "COM1";
        }
        @FXML
        private void COM2_Selected(ActionEvent event) {
            SelectedPort = "COM2";
        }
        @FXML
        private void COM3_Selected(ActionEvent event) {
            SelectedPort = "COM3";
        }
        @FXML
        private void COM4_Selected(ActionEvent event) {
            SelectedPort = "COM4";
        }
        @FXML
        private void COM5_Selected(ActionEvent event) {
            SelectedPort = "COM5";
        }
        @FXML
        private void COM6_Selected(ActionEvent event) {
            SelectedPort = "COM6";
        }
        @FXML
        private void COM7_Selected(ActionEvent event) {
            SelectedPort = "COM7";
        }   
        
        /**
        * Name: mnu_ConnectAction
        * Type: Method
        * Description: This mothod is linked with FXML Component.
        * 
        * @param event 
        */
        @FXML
        private void mnu_ConnectAction(ActionEvent event) throws UnsupportedCommOperationException {
           if (SelectedPort.contains("COM")){
                CurrentWeather.SetSelectedPort(SelectedPort);
            if(CurrentWeather.OpenConnection()){
                RefreshWeatherThread = new Timer();
              isConnected = true;
              RefreshWeatherThread.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                           public void run() {
                                  try{
                                    WeatherData = CurrentWeather.GetWeather();
                                  }catch(Exception e){
                                     e.printStackTrace();
                                  }
                                   WeatherDataToApp(WeatherData);
                                  
                          }
                        });
                    }
                },0,1000);
              log.Start(); // must go on connect
              mnu_Disconnect.setDisable(false);
            }
            mnu_connect.setDisable(true);
            
           }else{
             Alert alert = new Alert(AlertType.INFORMATION, "Για να ξεκινήσει το πρόγραμμα χρειάζεται να επιλέξετε την θύρα όπου είναι συνδεδεμένος ο μετεορωλογικός σταθμός. Για περισσότερες πληροφορορίες ανατρέξτε στην βοήθεια του προγράμματος.", ButtonType.OK);
             alert.showAndWait();
           }
        }
        
        @FXML
        private void mnu_DisconnectAction(ActionEvent event) {
           //sets the background to default
           ClearScreen();
           mnu_connect.setDisable(false);
           RefreshWeatherThread.cancel();
           CurrentWeather.CloseConnection();
        }
        
        @FXML
        private void mnu_ExitAction(ActionEvent event) throws Exception{
            //Close connection with arduino 
            if (isConnected){
                isConnected = CurrentWeather.CloseConnection();
                System.exit(1);
            }else{
                System.exit(1);
            }
        }
        @FXML
        private void btn_help_Action(ActionEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/graphics/windows/HelpScreen.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(432);
                stage.setWidth(620);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        stage.hide();
                    }
                });
                stage.setTitle("Βοήθεια");
                stage.setScene(scene);
                stage.setResizable(false);
                // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                // stage.getIcons().add(icon);
                stage.show();
        }
        /**
         * Name: mnu_AboutAction 
         * Type: Method
         * Description: Displays the About Window.
         * @param event
         * @throws IOException 
         */
        @FXML
        private void mnu_AboutAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/graphics/windows/AboutScreen.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(432);
                stage.setWidth(683);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        stage.hide();
                    }
                });
                stage.setTitle("Σχετικά με...");
                stage.setScene(scene);
                stage.setResizable(false);
                // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                // stage.getIcons().add(icon);
                stage.show();
        }
    // ==== END OF FXML COMPONENTS ACTION =====
  
    /**
     * This function change the Style (CSS) on Background Panel and sets photo based on weather condition.
     * to declare Weather condition I use Condtition_Identifier class on meteo.util package.
     * @param input
     * @return 
     */
    public boolean ChangeBackground(String input){
        //FadeInEffect(background);
        String CSSCode = "-fx-background-image: url('file://../res/background/"+input+"\');";
        background.setStyle(CSSCode);
        return true;
    }
    public void BackgroundTimer(){
        Background_Update = new Timer();
       Background_Update.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                           public void run() {
                               ChangeBackground(new Condition_Identifier(WeatherData).BackgroundName());   
                           }
                        });
                    }
                },0,1000);
              log.Start(); // must go on connect 
    }
    /**
     * Fade in Effect on given panel
     * @param selectedComponent 
     */
  
    public void FadeInEffect(Pane selectedComponent){
        FadeTransition background_fadein = new FadeTransition(Duration.millis(3000), selectedComponent);
        background_fadein.setFromValue(0.0);
        background_fadein.setToValue(1.0);
        background_fadein.play();
    }
    /**
     * Fade in Effect out given panel
     * @param selectedComponent 
     */
    public void FadeOutEffect(Pane selectedComponent){
        FadeTransition background_fadeout = new FadeTransition(Duration.millis(3000), selectedComponent);
        background_fadeout.setFromValue(1.0);
        background_fadeout.setToValue(0.0);
        background_fadeout.play();
    }
    /**
     * Name: StartDateTime
     * Type: Method
     * Description: This method starts the clock. Java Timer is used to avoid 
     * Thread and is use to update Date and Time Info. Timer works every Second
     */
    public void DateTimeProvider(){
        Timer DateTimeTimer = new Timer();
             DateTimeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                           public void run() {
                                  lbl_time.setText(DateTimeInfo.GetTime());
                                  lbl_date.setText(DateTimeInfo.GetDate());
                          }
                        });
                    }
                },0,500);
    }

    /**
     * Name: WeatherDataToApp
     * Type: Method
     * Description: This method sets weather data and text from arduino to lables,
     * Weather data is stored in an object that the method use it as input.
     * @param input 
     */
    public void WeatherDataToApp(C_WeatherData input){
        shape_line.setVisible(true);
        lbl_more.setVisible(true);
        try{
            lbl_temperature.setText(String.valueOf(input.getTempratureC())+"°C");
            lbl_humidity_txt.setText("Υγρασία:  "+String.valueOf(input.getHumidity())+"%");
            lbl_rainin_txt.setText("Βροχόπτωση:  "+String.valueOf(input.getRainIn())+" mm/h");
            lbl_windspeed_txt.setText("Ταχύτητα ανέμου:  "+String.valueOf(input.getWindSpeedKmh())+" Kmh");
            lbl_winddir_txt.setText("Κατεύθυνση ανέμου:  "+input.get_Direction_String());
            lbl_presoure_txt.setText("Βαρομετρική πίεση:  "+String.valueOf(input.getPressure())+" hpa");
            lbl_lightlvl_txt.setText("Επίπεδο φωτός:  "+String.valueOf(input.getLight())+"%");
            lbl_RealFeel.setText("Real Feel:  "+String.valueOf(input.getRealFeelC())+"°C");
            lbl_rainin_txt.setText("Βροχόπτωση: "+String.valueOf(input.getRainIn()));
        }catch(NoSuchMethodError|NumberFormatException e){
            e.printStackTrace();
        }
     }
  
    public void ClearScreen(){
        lbl_app_title.setVisible(false);
        lbl_temperature.setText("");
        lbl_humidity_txt.setText("");
        lbl_windspeed_txt.setText("");
        lbl_rainin_txt.setText("");
        lbl_winddir_txt.setText("");
        lbl_presoure_txt.setText("");
        lbl_lightlvl_txt.setText("");
        lbl_RealFeel.setText("");
        shape_line.setVisible(false);
        lbl_more.setVisible(false);
    }
                
    private Timer RefreshWeatherThread;
    private Timer Background_Update;
    private boolean isConnected;
    private String SelectedPort = "null";
    private Analytics log = new Analytics();
    private Date_Time DateTimeInfo = new Date_Time();
    private C_WeatherData WeatherData;
    private Data_Interface CurrentWeather = new Data_Interface();     
}