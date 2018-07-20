package Thread;
import java.util.*;

public class TrainThread extends RunnableThread {

    Timer trainTimer = new Timer ();
    private static int arrivalDelay = 6000; //TRAIN TAKES 8 SECONDS TO MOVE TO NEXT STATION
    private static int departureDelay = 8000; //TRAIN TAKES 8 SECONDS TO MOVE TO NEXT STATION
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
        trainTimer.scheduleAtFixedRate(arrivalTask, 0000, arrivalDelay);
        trainTimer.scheduleAtFixedRate(departureTask, 2000, departureDelay);

        //System.out.println("current time of execution: "+System.currentTimeMillis() % 1000);
    }

    TimerTask arrivalTask =  new TimerTask(){
        public void run(){
            station++;
            if (station > 8){
                arrivalTask.cancel();
            } else {
                System.out.println("Train " + trainnum + " is arriving at station " + station);
            }
        }
    };

    TimerTask departureTask =  new TimerTask() {
        public void run(){
            if (station > 8){
                departureTask.cancel();
            } else {
                System.out.println("Train " + trainnum + " is leaving station " + station);
            }
        }
    };

}