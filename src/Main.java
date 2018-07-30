import javafx.animation.*;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.net.URL;
import java.util.*;

public class Main extends Application implements Initializable{

    @FXML
    javafx.scene.control.Button station1AddButton;
    @FXML
    javafx.scene.control.Button station2AddButton;
    @FXML
    javafx.scene.control.Button station3AddButton;
    @FXML
    javafx.scene.control.Button station4AddButton;
    @FXML
    javafx.scene.control.Button station5AddButton;
    @FXML
    javafx.scene.control.Button station6AddButton;
    @FXML
    javafx.scene.control.Button station7AddButton;
    @FXML
    javafx.scene.control.Button station8AddButton;
    @FXML
    Text trainNumber;
    @FXML
    Spinner capacitySpinner;
    @FXML
    Button deploytrain;

    public static Stage primaryStage;
    public static Pane rootPane;
    public static int numOfTrains = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CalTrain c;
    public ArrayList<Station> allStations = new ArrayList<Station>();
    public ArrayList<Train> allTrains = new ArrayList<Train>();

    int totalPassengers = 34;
    int passengersLeft = totalPassengers;	// Passengers left to be picked up
    int passengersServed = totalPassengers;	// Passengers who haven't arrived to their destination
    boolean trainsReturned = true;			// If trains haven't returned to Station 0

    /* Program running-related variables */
    int totalPassengersBoarded = 0;
    int totalNumSeats = 0;
    int threadsCompleted = 0;
    int maxFreeSeats = 5;
    int trainCtr = 0;
    int passCtr = 10;
    int maxInsert = 0;
    boolean loadTrainReturned = false;

    /* Temporary Variables */
    int inStationNum, freeSeats;
    Passenger tempRobot;
    Train tempTrain;
    public static int currentTrain = 0;
    public static int totalPassServed = 0;

    public static boolean pause = false;
    public int passLeft = 0;
    public int nextStation = 0;

    public static int decommissioned = 0;
    public Timer timer = new Timer();
//////////////////////////////////////////////////////////////////////////
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

        this.c = new CalTrain(this);
        createStations();
        createPassengers();
        if(totalPassengers > 0)
            createTrain();

        update();
    }
    ////////////////////////////////////////////////////////////////
    public Station[] stations = new Station[8];

    public void createStations(){
        passLeft = 0;
        System.out.println("Initializing stations");
        for(int i=0;i<8;i++) {
            allStations.add(c.station_init(i));
            if (i >= 1 && i < 8) {
                allStations.get(i-1).setNextStation(allStations.get(i));
//                allStations.get(i).setLeftStation(allStations.get(i-1));
            }
//            System.out.println(allStations.get(i).displayNextStations());
        }
    }

    public void createPassengers(){
        /* Initialize Passengers */
//        int[] inStation = {1,1,1,1,1,1,
//                2,2,2,2,2,2,2,2,
//                3,3,3,3,
//                4,4,4,4,4,
//                5,5,5,
//                6,6,
//                7,7,7,
//                8,8,8};
//        int[] outStation = {2,3,4,8,8,8,
//                5,5,6,6,6,7,7,7,
//                4,6,6,6,
//                5,5,6,8,1,
//                2,8,1,
//                7,4,
//                8,8,1,
//                3,6,6};
//        for(int i=0;i<totalPassengers;i++)
//        {
//            new Passenger(allStations.get(inStation[i]-1), c, i, allStations.get(outStation[i]-1));
//            threadsCompleted++;
//            try {Thread.sleep(300);} catch(Exception e){}
//        }

//        for(int i = 0; i < allStations.size(); i++){
//            int pass = allStations.get(i).getPassWaiting().size();
////            p.createStation(i, pass, allStations.get(i).getWaitPassCount(true));
//            System.out.println("Station: " + (i + 1) + " Passengers: " + pass);
//        }
//        a();
    }

    public void addPassenger(int in, int out, boolean direction){
        inStationNum = in;
        Passenger temp = new Passenger(allStations.get(in), c, totalPassengers++, allStations.get(out));
//        if(temp.getDirection() != direction){
//            allStations.get(in).decWaitPass(temp, temp.getDirection());
//            temp.setDirection(direction);
//            allStations.get(in).addPassenger(temp, direction);
//        }

        System.out.println("Added Pass " + totalPassengers + " at Station " + in + " " + direction);
        threadsCompleted++;
        try {Thread.sleep(300);} catch(Exception e){}

        for(int i = 0; i < allStations.size(); i++){
            int pass = allStations.get(i).getPassWaiting().size();
//            p.createStation(i, pass, allStations.get(i).getWaitPassCount(true)); //add passenger gui
            System.out.println("Station: " + (i + 1) + " Passengers: " + pass);
        }
    }

//    public void a(){
//        for(int i = 0; i < t.stations.length; i++){
//            int j = i;
//            t.stations[i].setOnMouseClicked(e -> {
//                currentStation = j;
//                p.createStation(j, allStations.get(j).getWaitPassCount(false), allStations.get(j).getWaitPassCount(true));
//                layout.setRight(p.layout);
//            });
//        }
//
//        for(int i = 0; i < t.trains.size(); i++){
//            int j = i;
//            t.getTrain(i).setOnMouseClicked(e -> {
//                passLeft = allTrains.get(j).getRiders().size();
//                nextStation = allTrains.get(j).getBoardStation().getStationNum();
//            });
//        }
//    }


    public void createTrain(){
//        freeSeats = 5; //FROM CAPACITY GUI
        totalNumSeats += freeSeats;

        loadTrainReturned = false;
        tempTrain = new Train(allStations.get(0), c, freeSeats, trainCtr);
        loadTrainReturned = true;
        allTrains.add(tempTrain);
        trainCtr++;

//        p.train(allTrains.size(), tempTrain.getRiders().size(), tempTrain.getBoardStation().getStationNum());
//        t.createTrain(this); //ADD TRAIN GUI
    }

    public void update(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                /*
                 * 1. Check if a train has reached a station
                 * 		a. stop trains behind it
                 * 		b. board passengers
                 *
                 */
                logic();
            }
        }, 0, 100);
    }

    public void logic(){
        for(int i = 0; i < allTrains.size(); i++){
            currentTrain = i;
            try{Thread.sleep(2500);} catch(Exception e) {e.printStackTrace();}

//            if(pause){
//                try{
//                    System.out.println("Threads are asleep");
//                    Thread.sleep(p.a.getTime());
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//                finally{
//                    pause = false;
//                    t.getAnim(i).start();
//                }
//            }
//
//            boolean tempDirection = allTrains.get(i).getDirection();

            int threadsToReap = -1;
            int threadsReaped = 0;

            //min : (passengers waiting, free seats)
            threadsToReap = Math.min(allTrains.get(i).getCurrStation().passWaiting.size(),
                                     allTrains.get(i).getAvailable());

            while(threadsReaped < threadsToReap) {
                boolean boarded = false;
                if(threadsCompleted > 0) {
                    if(allTrains.get(i).getCurrStation().passWaiting.size() > 0)//may nag hihintay
                        boarded = c.station_on_board(allTrains.get(i).getCurrStation(),             //board mo na woooo
                                                     allTrains.get(i).getCurrStation().passWaiting.get(0),
                                threadsReaped + 1 == threadsToReap);
                    if(boarded)
                        threadsReaped++;
                }
            }

            passengersLeft -= threadsReaped;
            totalPassengersBoarded += threadsReaped;

            if(threadsToReap != threadsReaped)
                System.out.println("Error: Too many passengers on this train!");
            try{Thread.sleep(800);} catch(Exception e){e.printStackTrace();}

            /* Make sure all trains return to first station */
            if (totalPassServed == totalPassengers) {
                allTrains.get(i).stopRun();
                System.out.println("Train " + allTrains.get(i).getTrainNum() + " is decommissioned.");
                decommissioned++;
                allTrains.remove(allTrains.get(i));
//                t.anims.remove(i);
//                t.trains.remove(i);
                i--;
                if (allTrains.size() == 0) {
                    System.out.println("All trains are gone!");
                }
            }

        }
    }

    public void checkStop(){
        for(int i = 1; i < allTrains.size() - 2; i++){
            //if sabay yungtrains
            if(allTrains.get(i - 1).getCurrStation().getStationNum() == allTrains.get(i).getCurrStation().getStationNum() + 1){
//                t.getAnim(i).stop(); //ADD SABAY GUI
            }

            //overtake trains
//            if(allTrains.get(i - 1).getCurrStation().getStationNum() > allTrains.get(i).getCurrStation().getStationNum() + 1)
//                t.getAnim(i).start();
        }

        if(currentTrain > 0){
            try{
//				if(allTrains.get(currentTrain).getBoardStation().checkNextQueue(allTrains.get(currentTrain - 1), allTrains.get(currentTrain).getDirection()))
//					t.getAnim(currentTrain).stop();
//				else
//					t.getAnim(currentTrain).start();
            }catch(Exception e){}
        }


    }

//    public void resetTrains(){
//        t.resetLayout();
//    }

//
//    public void resetStations(){
//        for(int i = 0; i < t.stations.length; i++){
//            p.createStation(i, allStations.get(i).getWaitPassCount(false), allStations.get(i).getWaitPassCount(true));
//            layout.setRight(p.layout);
//        }
//        p.createStation(currentStation, allStations.get(currentStation).getWaitPassCount(false), allStations.get(currentStation).getWaitPassCount(true));
//        layout.setRight(p.layout);
//    }
//
//    public void resetTrainPrev(){
//        if(allTrains.size() > 0)
//            p.train(allTrains.size(), allTrains.get(currentTrain).getRiders().size(), allTrains.get(currentTrain).getBoardStation().getStationNum());
//    }
//
//    public void pause(){
//        for(int i = 0; i < allTrains.size(); i++){
//
//            if(pause){
//                try{
//                    System.out.println("Threads are asleep");
////                    t.getAnim(i).stop();
////                    Thread.sleep(p.a.getTime());
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//                finally{
//                    pause = false;
////                    t.getAnim(i).start();
//                }
//            }
//        }
//
//    }

////////////////////////////////////////////////////////////////

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        rootPane = new Pane();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View.fxml"));

        primaryStage.setTitle("CalTrainII Automation (Process Synchronization)");
        primaryStage.setResizable(false);
        rootPane.getChildren().add(root);
        primaryStage.setScene(new Scene(rootPane, 1077, 690));
        primaryStage.show();
    }

    @FXML
    public void newTrain() throws Exception{
        ImageView imgview = new ImageView();
        File file = new File("src/res/cutetrain.png");
        Image image = new Image(file.toURI().toString());

        imgview.setImage(image);
        imgview.setFitHeight(52);
        imgview.setFitWidth(210);
        imgview.setLayoutX(-200);
        imgview.setLayoutY(220);
        imgview.toFront();

        //NEW TRAIN LOGIC!!!!!!!!!!!!!!!!
        Thread thread = new Thread(() -> {
            double nextStation = 0;
            while (nextStation <= 8){
                nextStation ++;
                if (nextStation >= 5){
                    imgview.setLayoutX(-1300);
                    imgview.setLayoutY(539);
                }
                moveTrainToNextStn(imgview, nextStation);

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        rootPane.getChildren().add(imgview);
        numOfTrains++;
        trainNumber.setText(numOfTrains+"");
    }

    public void moveTrainToNextStn(Node imageview, double nextStation) {
        double xPos = 0;
        if (nextStation == 1)
            xPos = 220;
        else if (nextStation == 2)
            xPos = 330;
        else if (nextStation == 3)
            xPos = 280;
        else if (nextStation == 4)
            xPos = 250;
        else if (nextStation == 5)
            xPos = 220;
        else if (nextStation == 6)
            xPos = 330;
        else if (nextStation == 7)
            xPos = 260;
        else if (nextStation == 8)
            xPos = 230;
        else if (nextStation == 9) // (CREATED JUST TO REMOVE THE TRAIN FROM GUI)
            xPos = 270;

        TranslateTransition transitn = new TranslateTransition(Duration.millis(4000), imageview);
        transitn.setFromX(imageview.getTranslateX());
        transitn.setFromY(imageview.getTranslateY());
        transitn.setByX(xPos);
        transitn.setRate(2);
        transitn.setInterpolator(Interpolator.LINEAR);
        transitn.play();
    }

    @FXML
    public void addPassenger(Button b)  {

        ImageView imgview = new ImageView();
        File file = new File("src/res/robot1.png");
        Image image = new Image(file.toURI().toString());
        imgview.setImage(image);
        imgview.setFitHeight(46);
        imgview.setFitWidth(48);

        TranslateTransition transitn = new TranslateTransition(Duration.millis(4000), imgview);

        Random rand = new Random();

        if (b.getId().compareToIgnoreCase("station1AddButton") == 0) {
            int value = rand.nextInt(215-50)+50;
            imgview.setLayoutX(value);
            imgview.setLayoutY(220);
            //NEW PASSENGER LOGIC!!!!!!!!!!!!!!!!
        }
        else if (b.getId().compareToIgnoreCase("station2AddButton") == 0){
            int value = rand.nextInt(461-375)+375;
            imgview.setLayoutX(value);
            imgview.setLayoutY(220);

            imgview.toBack();
        }
        else if (b.getId().compareToIgnoreCase("station3AddButton") == 0){
            int value = rand.nextInt(760-635)+635;
            imgview.setLayoutX(value);
            imgview.setLayoutY(220);
        }
        else if (b.getId().compareToIgnoreCase("station4AddButton") == 0){
            int value = rand.nextInt(970-906)+906;
            imgview.setLayoutX(value);
            imgview.setLayoutY(220);
        }
        else if (b.getId().compareToIgnoreCase("station5AddButton") == 0){
            int value = rand.nextInt(170-35)+35;
            imgview.setLayoutX(value);
            imgview.setLayoutY(537);
        }
        else if (b.getId().compareToIgnoreCase("station6AddButton") == 0){
            int value = rand.nextInt(454-334)+334;
            imgview.setLayoutX(value);
            imgview.setLayoutY(537);
        }
        else if (b.getId().compareToIgnoreCase("station7AddButton") == 0){
            int value = rand.nextInt(697-619)+619;
            imgview.setLayoutX(value);
            imgview.setLayoutY(537);
        }
        else if (b.getId().compareToIgnoreCase("station8AddButton") == 0){
            int value = rand.nextInt(998-830)+830;
            imgview.setLayoutX(value);
            imgview.setLayoutY(537);
        }

        transitn.setByX(5);
        transitn.setRate(3);

        transitn.setInterpolator(Interpolator.EASE_IN);
        transitn.setCycleCount(Animation.INDEFINITE);
        transitn.play();

        rootPane.getChildren().add(imgview);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
