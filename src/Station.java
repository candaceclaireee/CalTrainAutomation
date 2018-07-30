import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station {
    int stationNum;
    Train currTrain;
    Station nextStation;
    ArrayList <Passenger> passWaiting = new ArrayList<Passenger>();

    //for synch
    private Condition train_arrived;
    private Condition all_pass_seated;
    private Lock on_board;
    private Lock lock;

    public Station(int stationNum) {
        //for synch
        train_arrived = new ReentrantLock().newCondition();
        all_pass_seated = new ReentrantLock().newCondition();
        on_board = new ReentrantLock();
        lock = new ReentrantLock();

        this.stationNum = stationNum;
    }

    public Condition getTrain_arrived() {
        return train_arrived;
    }

    public void setTrain_arrived(Condition train_arrived) {
        this.train_arrived = train_arrived;
    }

    public Condition getAll_pass_seated() {
        return all_pass_seated;
    }

    public void setAll_pass_seated(Condition all_pass_seated) {
        this.all_pass_seated = all_pass_seated;
    }

    public Lock getOn_board() {
        return on_board;
    }

    public void setOn_board(Lock on_board) {
        this.on_board = on_board;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
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

    public ArrayList<Passenger> getPassWaiting() {
        return passWaiting;
    }

    public void setPassWaiting(ArrayList<Passenger> passWaiting) {
        this.passWaiting = passWaiting;
    }

    public void deletePassWaiting (int passNum) {
        for (int i=0; i<passWaiting.size(); i++){
            if (passWaiting.get(i).getPassNum()==passNum){
                passWaiting.remove(i);
            }
        }
    }

    public void addPassWaiting (Passenger passenger) {
        passWaiting.add(passenger);
    }

    public Station getNextStation() {
        return this.nextStation;
    }

    public void setNextStation(Station next) {
        this.nextStation = next;
    }

    /* Synchronization Functions */
    public void waitPassSeated() {
        try {
            all_pass_seated.wait();
            Thread.sleep(1500);
        } catch(Exception e){}
    }

    public void signalPassSeated() {
        try {
            all_pass_seated.signal();
        } catch(Exception e){}
    }

    public void waitTrain() {
        try {
            train_arrived.wait();
        } catch(Exception e){}
    }

    public void signalTrain() {
        try {
            train_arrived.signal();
        } catch(Exception e){}
    }
}
