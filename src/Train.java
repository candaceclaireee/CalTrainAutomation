import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Train implements Runnable {
    int trainNum;
    int capacity;
    int available;
    int taken;
    boolean continueRun;
    Station currStation;
    ArrayList <Passenger> passBoarded = new ArrayList<Passenger>();
    CalTrain sync;
    ImageView sprite;
    Thread trainThread = new Thread();

    public Train(Station currStation, CalTrain sync, int capacity, int trainNum) {
        this.sync = sync;
        this.trainNum = trainNum;
        this.capacity = capacity;
        this.available = capacity;
        this.currStation = currStation;
        this.taken = 0;
        continueRun = true;
        trainThread.start();
        sprite = null;
    }

    @Override
    public void run() {


        while(getContinueRun()) {

            System.out.println("train "+this.trainNum+" thread started");
//			System.out.println("thread alive");
            if (currStation.getCurrTrain() == null)
            {
                /* Train arrives at station */
                currStation.getLock().lock();
                currStation.setCurrTrain(this);
                //	boardStation.getLock().unlock();

                /* Executes loading train at station */
                sync.station_load_train(currStation, this);

                /* Train leaves station */
                //	boardStation.getLock().lock();
                currStation.setCurrTrain(null);
                currStation.getLock().unlock();

                /* Train plans next destination */
//                currStation.removeFromQueue(direction); //WAT IS DIS FOR
                currStation = currStation.getNextStation();
//                currStation.addTrainQueue(this, direction); WAT IS DIS FOR
                System.out.println("Train " + trainNum + " is  going next to Station "
                        + (currStation.getStationNum() + 1));

                if(trainNum > 0){
                    if(sync.getGame().allTrains.get(trainNum - 1 - Main.decommissioned).getCurrStation().getStationNum() == currStation.getStationNum() + 1){
//                        if(sync.getGame().allTrains.get(trainNum - 1 - Game.decommissioned).getDirection() == direction)
                            try{Thread.sleep(2000);}catch(Exception e){}
                    }
                }
            }
            try{Thread.sleep(1700);} catch(Exception e) {}
        }

    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
    public ImageView getSprite(){
        return sprite;
    }

    public boolean getContinueRun() {
        return continueRun;
    }

    public void restart(){
        continueRun = true;
    }

    public void stopRun() {
        continueRun = false;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public void deletePassBoarded (int passNum) {
        for (int i=0; i<passBoarded.size(); i++){
            if (passBoarded.get(i).getPassNum()==passNum){
                passBoarded.remove(i);
            }
        }
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Station getCurrStation() {
        return currStation;
    }

    public void setCurrStation(Station currStation) {
        this.currStation = currStation;
    }

    public ArrayList<Passenger> getPassBoarded() {
        return passBoarded;
    }

    public void setPassBoarded(ArrayList<Passenger> passBoarded) {
        this.passBoarded = passBoarded;
    }

    public void addPassBoarded (Passenger passenger) {
        passBoarded.add(passenger);
    }
}