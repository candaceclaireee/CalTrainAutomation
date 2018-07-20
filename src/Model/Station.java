package Model;

import java.util.ArrayList;

public class Station {

    private int stationNum;
    private Train currTrain;
    private ArrayList<Passenger> passengersWaiting;

    public Station () {

    }

    public int getStationNum() {
        return stationNum;
    }

    public void setStationNum(int stationNum) {
        this.stationNum = stationNum;
    }

    public Train getCurrTrain() {
        return currTrain;
    }

    public void setCurrTrain(Train currTrain) {
        this.currTrain = currTrain;
    }

    public ArrayList<Passenger> getPassengersWaiting() {
        return passengersWaiting;
    }

    public void setPassengersWaiting(ArrayList<Passenger> passengersWaiting) {
        this.passengersWaiting = passengersWaiting;
    }
}
