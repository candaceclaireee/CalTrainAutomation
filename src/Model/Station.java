package Model;

import java.util.ArrayList;

public class Station{

    private int stationNum;
    private Train currTrain;
    private ArrayList<Passenger> passengersWaiting;

    public Station (int stationNum) {
        this.stationNum = stationNum;
        passengersWaiting = new ArrayList<Passenger>();
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

    public void addPassengersWaiting(Passenger passenger) {
        passenger.setCurrStation(this);
        this.passengersWaiting.add(passenger);
    }

    public void subPassengersWaiting(Passenger passenger) {
        for (int i=0; i< passengersWaiting.size(); i++) {
            if (currTrain.getAvailableSeats() > 0) {
                if (passenger.getPassengerNum() == passengersWaiting.get(i).getPassengerNum()) {
                    System.out.println("passenger " + passengersWaiting.get(i).getPassengerNum() + " boarded");
                    passengersWaiting.get(i).setCurrTrain(currTrain);
                    passengersWaiting.get(i).load_passenger();
                    passengersWaiting.remove(i);
                }
            }
        }
    }

}
