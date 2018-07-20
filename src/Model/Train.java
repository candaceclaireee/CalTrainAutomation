package Model;

import java.util.ArrayList;
import Thread.RunnableThread;
import Thread.PassengerThread;
import Thread.TrainThread;

public class Train {

    private int trainNum;
    private int capacity;
    private int currStation;
    private int nextStation;
    private ArrayList<Passenger> passengersBoarded;
    private TrainThread trainThrd;

    public Train (int trainNum) {
        this.trainNum = trainNum;

        trainThrd = new TrainThread("trainThrd");
        trainThrd.setTrainNum(trainNum);
        trainThrd.start();
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

    public int getCurrStation() {
        return currStation;
    }

    public void setCurrStation(int currStation) {
        this.currStation = currStation;
    }

    public int getNextStation() {
        return nextStation;
    }

    public void setNextStation(int nextStation) {
        this.nextStation = nextStation;
    }

    public ArrayList<Passenger> getPassengersBoarded() {
        return passengersBoarded;
    }

    public void setPassengersBoarded(ArrayList<Passenger> passengersBoarded) {
        this.passengersBoarded = passengersBoarded;
    }
}
