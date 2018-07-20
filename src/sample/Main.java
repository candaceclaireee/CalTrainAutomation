package sample;

import Model.Passenger;
import Model.Train;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 925, 755));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
//        ArrayList<Passenger> newPassengers = new ArrayList<Passenger>();
//        ArrayList<Train> newTrains = new ArrayList<Train>();
//
//        Scanner sc = new Scanner(System.in);
//        int input;
//
//        System.out.println("enter 0 to add passenger, 1 to add train, 2 to exit");
//        input = sc.nextInt();
//
//        while (input!=2) {
//            if (input == 0) {
//                newPassengers.add(new Passenger());
////                System.out.println("passenger");
//            } else if (input == 1) {
//                newTrains.add(new Train());
////                System.out.println("train");
//            }
//            System.out.println("enter 0 to add passenger, 1 to add train, 2 to exit");
//            input = sc.nextInt();
//        }
    }
}
