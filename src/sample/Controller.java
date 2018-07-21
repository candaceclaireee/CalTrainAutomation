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

    public Controller() {
        stations[0]= new Station(1);
        stations[1]= new Station(2);
        stations[2]= new Station(3);
        stations[3]= new Station(4);
        stations[4]= new Station(5);
        stations[5]= new Station(6);
        stations[6]= new Station(7);
        stations[7]= new Station(8);

    }

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
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 1,1));
                stations[0].addPassengersWaiting(newPassengers.get(newPassengers.size()-1));
                //station_wait_for_train(Station)
                newPassengers.get(newPassengers.size()-1).station_Wait_For_Train(stations[0]);
            }
            else if(b.getText().compareToIgnoreCase("Station 2") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 2,2));
            }
            else if(b.getText().compareToIgnoreCase("Station 3") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 3,3));
            }
            else if(b.getText().compareToIgnoreCase("Station 4") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 4,4));
            }
            else if(b.getText().compareToIgnoreCase("Station 5") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 5,5));
            }
            else if(b.getText().compareToIgnoreCase("Station 6") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 6,6));
            }
            else if(b.getText().compareToIgnoreCase("Station 7") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 7,7));
            }
            else if(b.getText().compareToIgnoreCase("Station 8") == 0){
                int passengerNum = newPassengers.size()+1;
                station1TextArea.appendText("Passenger "+ passengerNum +" arrived in Station 1\n");
                newPassengers.add(new Passenger(passengerNum, 8,8));
            }
    }


    @FXML
    public void addTrain(Button b) {

        trainnumber++;
        trainNumber.setText(trainnumber+"");

        Train newtrain = new Train(trainnumber,40);     //get capacity
        newtrain.setCapacity(Integer.parseInt(capacitySpinner.getValue() + ""));
        newTrains.add(newtrain);

        Thread thread = new Thread(() -> {
            int index = 0;
            while (index < 8){
                if(index!=0){
                    stations[index-1].setCurrTrain(null);
                }

                index++;
                newtrain.setCurrStation(stations[index]);
                stations[index].setCurrTrain(newtrain);
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

    public void updateStations(int trainnumber, Station station){
        if (station.getStationNum() == 1) {
            station1TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 2){
            station2TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 3){
            station3TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 4){
            station4TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 5){
            station5TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 6){
            station6TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 7){
            station7TextArea.appendText("Train " + trainnumber +  " is at station " + station + "\n");
        } else if (station.getStationNum() == 8){
            station8TextArea.appendText("Train is arriving station " + station + "\n");
        }
    }

}
