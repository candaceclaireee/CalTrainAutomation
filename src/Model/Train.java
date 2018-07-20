package Model;

import java.util.ArrayList;
import Thread.RunnableThread;
import Thread.PassengerThread;
import Thread.TrainThread;

public class Train {

    private int trainNum;
    private int capacity;
    private Station currStation;
    private Station nextStation;
    private ArrayList<Passenger> passengersBoarded;
    private RunnableThread trainThrd;

    public Train () {
        trainThrd = new TrainThread("trainThrd");
        trainThrd.start();
    }

    public void start(){
        //thread starts to run
    }

    public int getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(int trainNum) {
        this.trainNum = trainNum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Station getCurrStation() {
        return currStation;
    }

    public void setCurrStation(Station currStation) {
        this.currStation = currStation;
    }

    public Station getNextStation() {
        return nextStation;
    }

    public void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
    }

    public ArrayList<Passenger> getPassengersBoarded() {
        return passengersBoarded;
    }

    public void setPassengersBoarded(ArrayList<Passenger> passengersBoarded) {
        this.passengersBoarded = passengersBoarded;
    }
}
