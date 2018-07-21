package Model;

import Thread.RunnableThread;
import Thread.PassengerThread;
import Thread.TrainThread;

public class Passenger {

    private int passengerNum;
    private int stationSrc;
    private int stationDest;
    private int stationCurr;
    private RunnableThread passengerThrd;

    public Passenger (int passengerNum, int stationSrc, int stationCurr) {
        this.passengerNum = passengerNum;
        this.stationSrc = stationSrc;
        this.stationCurr = stationCurr;
        passengerThrd = new PassengerThread("passengerThrd");
        passengerThrd.start();
    }

    public int getStationSrc() {
        return stationSrc;
    }

    public void setStationSrc(int stationSrc) {
        this.stationSrc = stationSrc;
    }

    public int getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }

    public int getStationDest() {
        return stationDest;
    }

    public void setStationDest(int stationDest) {
        this.stationDest = stationDest;
    }

    public int getStationCurr() {
        return stationCurr;
    }

    public void setStationCurr(int stationCurr) {
        this.stationCurr = stationCurr;
    }

    public void station_Wait_For_Train(Station station){
        while (station.getCurrTrain()== null){
            //do nothing
        }
        if (station.getCurrTrain()!= null){
            station.getCurrTrain().station_Load_Train(station, this);
        }
    }
}
