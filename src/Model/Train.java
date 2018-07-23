package Model;

import java.util.ArrayList;
import Thread.RunnableThread;
import Thread.PassengerThread;
import Thread.TrainThread;

public class Train {

    private int trainNum;
    private int capacity;
    private int takenSeats;
    private int availableSeats;
    private Station currStation;
    private Station nextStation;
    private ArrayList<Passenger> passengersBoarded;
    private TrainThread trainThrd;

    public Train (int trainNum, int capacity) {
        this.trainNum = trainNum;
        this.capacity = capacity;
        this.availableSeats = capacity;
        this.takenSeats = 0;

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

    public int getTakenSeats() {
        return takenSeats;
    }

    public void setTakenSeats(int takenSeats) {
        this.takenSeats = takenSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

    public void addPassengersBoarded(Passenger passenger) {
        this.passengersBoarded.add(passenger);
    }

    public void subPassengersBoarded(Passenger passenger) {
        for (int i=0; i<passengersBoarded.size(); i++){
            if (passenger.getPassengerNum()==passengersBoarded.get(i).getPassengerNum()){
                passengersBoarded.remove(i);
            }
        }
    }

    //station_load_train(Station, availableSeats)
    public void station_Load_Train(Station station, Passenger passenger){
        if(availableSeats>=1){
            //station.subPassengersWaiting(passenger);
            //addPassengersBoarded(passenger);
            availableSeats--;
            takenSeats++;
        }
    }
}
