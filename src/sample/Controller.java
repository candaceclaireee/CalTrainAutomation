package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
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





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);

        capacitySpinner.setValueFactory(valueFactory);
    }
}
