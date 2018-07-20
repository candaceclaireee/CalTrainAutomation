package Model;

import Thread.RunnableThread;
import Thread.PassengerThread;
import Thread.TrainThread;

public class Passenger {

    private int stationSrc;
    private int stationDest;
    private int stationCurr;
    private RunnableThread passengerThrd;

    public Passenger (int stationSrc) {
        this.stationSrc = stationSrc;
        passengerThrd = new PassengerThread("passengerThrd");
        passengerThrd.start();
    }

    public int getStationSrc() {
        return stationSrc;
    }

    public void setStationSrc(int stationSrc) {
        this.stationSrc = stationSrc;
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
}
