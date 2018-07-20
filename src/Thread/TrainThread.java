package Thread;

import java.util.*;

public class TrainThread extends RunnableThread {

    Timer trainTimer = new Timer ();
    private static int arrivalDelay = 6000; // train arrives to station in 6s
    private static int departureDelay = 8000; // and leaves after 2s

    private int station = 0;
    public int trainnum;

    public TrainThread(String name) {
        super(name);
    }

    public void setTrainNum(int n){
        trainnum = n;
    }

    @Override
    public void run() {
        //trainTimer.scheduleAtFixedRate(arrivalTask, 0000, arrivalDelay);
        //trainTimer.scheduleAtFixedRate(departureTask, 2000, departureDelay);
        //System.out.println("current time of execution: "+System.currentTimeMillis() % 1000);
    }

    /*TimerTask arrivalTask =  new TimerTask(){
        public void run(){
            station++;
            if (station > 8){
                arrivalTask.cancel();
            } else {
                sample.Controller c = new sample.Controller();
                c.checkTrains(station,"Train " + trainnum + " is arriving at station " + station);
            }
        }
    };

    TimerTask departureTask =  new TimerTask() {
        public void run(){
            if (station > 8){
                departureTask.cancel();
            } else {
                sample.Controller c = new sample.Controller();
                c.checkTrains(station, "Train " + trainnum + " is leaving station " + station);
            }
        }
    }; */

}