package sample;

import Model.Passenger;
import Model.Train;
import Model.Station;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.application.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.lang.InterruptedException;

public class Controller implements Initializable {
    @FXML
    private  TextArea station1TextArea;
    @FXML
    private  TextArea station2TextArea;
    @FXML
    private  TextArea station3TextArea;
    @FXML
    private  TextArea station4TextArea;
    @FXML
    private  TextArea station5TextArea;
    @FXML
    private  TextArea station6TextArea;
    @FXML
    private  TextArea station7TextArea;
    @FXML
    private  TextArea station8TextArea;

    @FXML
    Button station1AddButton;
    @FXML
    Button station2AddButton;

    @FXML
    Button station3AddButton;

    @FXML
    Button station4AddButton;

    @FXML
    Button station5AddButton;
    @FXML
    Button station6AddButton;
    @FXML
    Button station7AddButton;

    @FXML
    Button station8AddButton;

    @FXML
    Text trainNumber;
    @FXML
    Spinner capacitySpinner;
    @FXML
    Button deploytrain;

    ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
    ArrayList<Train> newTrains = new ArrayList<Train>();
    Station[] stations = new Station[8];

    int trainnumber = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        station1AddButton.setOnAction(e -> addPassenger(station1AddButton));
        station2AddButton.setOnAction(e -> addPassenger(station2AddButton));
        station3AddButton.setOnAction(e -> addPassenger(station3AddButton));
        station4AddButton.setOnAction(e -> addPassenger(station4AddButton));
        station5AddButton.setOnAction(e -> addPassenger(station5AddButton));
        station6AddButton.setOnAction(e -> addPassenger(station6AddButton));
        station7AddButton.setOnAction(e -> addPassenger(station7AddButton));
        station8AddButton.setOnAction(e -> addPassenger(station8AddButton));

        deploytrain.setOnAction(e-> addTrain(deploytrain));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 40, 1);
        capacitySpinner.setValueFactory(valueFactory);
    }

    @FXML
    public void addPassenger(Button b){
            System.out.println(b.getText());
            if(b.getText().compareToIgnoreCase("Station 1") == 0){
                station1TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 1\n");
                newPassengers.add(new Passenger(1,1));
            }
            else if(b.getText().compareToIgnoreCase("Station 2") == 0){
                station2TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 2\n");
                newPassengers.add(new Passenger(2, 2));
            }
            else if(b.getText().compareToIgnoreCase("Station 3") == 0){
                station3TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 3\n");
                newPassengers.add(new Passenger(3, 3));
            }
            else if(b.getText().compareToIgnoreCase("Station 4") == 0){
                station4TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 4\n");
                newPassengers.add(new Passenger(4, 4));
            }
            else if(b.getText().compareToIgnoreCase("Station 5") == 0){
                station5TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 5\n");
                newPassengers.add(new Passenger(5, 5));
            }
            else if(b.getText().compareToIgnoreCase("Station 6") == 0){
                station6TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 6\n");
                newPassengers.add(new Passenger(6, 6));
            }
            else if(b.getText().compareToIgnoreCase("Station 7") == 0){
                station7TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 7\n");
                newPassengers.add(new Passenger(7, 7));
            }
            else if(b.getText().compareToIgnoreCase("Station 8") == 0){
                station8TextArea.appendText("Passenger "+ (newPassengers.size()+1) +" arrived in Station 8\n");
                newPassengers.add(new Passenger(8, 8));
            }
        }

    @FXML
    public void addTrain(Button b) {
        trainnumber++;
        trainNumber.setText(trainnumber+"");

        Train newtrain = new Train(trainnumber);
        newtrain.setCapacity(Integer.parseInt(capacitySpinner.getValue() + ""));
        newTrains.add(newtrain);

        Thread thread = new Thread(() -> {
            int station = 0;
            while (station < 8){
                station++;
                newtrain.setCurrStation(station);
                updateStations(newtrain.getTrainNum(), newtrain.getCurrStation());

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void updateStations(int trainnumber, int station){
        if (station == 1) {
            station1TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 2){
            station2TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 3){
            station3TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 4){
            station4TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 5){
            station5TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 6){
            station6TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 7){
            station7TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station == 8){
            station8TextArea.appendText("Train is arriving station " + station + "\n");
        }
    }

}
