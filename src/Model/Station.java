package Model;

import java.util.ArrayList;

public class Station{

    private int stationNum;
    private Train currTrain;
    private ArrayList<Passenger> passengersWaiting;

    private int recentlyBoarded;


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

    public int subPassengersWaiting(Passenger passenger) {
        for (int i=0; i< passengersWaiting.size(); i++) {
            if (currTrain.getAvailableSeats() > 0) {
                if (passenger.getPassengerNum() == passengersWaiting.get(i).getPassengerNum()) {
                    System.out.println("Passenger " + passengersWaiting.get(i).getPassengerNum() + " boarded train " + currTrain.getTrainNum());
                    recentlyBoarded = passengersWaiting.get(i).getPassengerNum();

                    passengersWaiting.get(i).setCurrTrain(currTrain);
                    passengersWaiting.get(i).setCurrStation(null);
                    passengersWaiting.get(i).load_passenger();
                    passengersWaiting.remove(i);

                    return recentlyBoarded;

               }
            }
        }
        return 0;
    }

}
