package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea station1TextArea;
    @FXML
    TextArea station2TextArea;
    @FXML
    TextArea station3TextArea;
    @FXML
    TextArea station4TextArea;
    @FXML
    TextArea station5TextArea;
    @FXML
    TextArea station6TextArea;
    @FXML
    TextArea station7TextArea;
    @FXML
    TextArea station8TextArea;

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

    ArrayList addPassengerButtons;
    ArrayList stationTextAreas;






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


        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 40, 1);
        capacitySpinner.setValueFactory(valueFactory);



    }
    @FXML
    public void addPassenger(Button b){
            System.out.println(b.getText());
            if(b.getText().compareToIgnoreCase("Station 1") == 0){
                station1TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 2") == 0){
                station2TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 3") == 0){
                station3TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 4") == 0){
                station4TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 5") == 0){
                station5TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 6") == 0){
                station6TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 7") == 0){
                station7TextArea.setText("bulos");
            }
            else if(b.getText().compareToIgnoreCase("Station 8") == 0){
                station8TextArea.setText("bulos");
            }

        }
}
