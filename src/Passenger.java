import javafx.scene.image.ImageView;

public class Passenger implements Runnable {
    CalTrain sync;
    int passNum;
    Station src;
    Station dest;
    Train trainIn;
    boolean boarded;
    ImageView sprite;
    Thread passThread = new Thread();

    public Passenger(Station src, CalTrain sync, int passNum, Station dest) {
        this.sync = sync;
        this.passNum = passNum;
        this.boarded = false;
        this.src = src;
        this.dest = dest;
        passThread.start();
        sprite = null;
    }
    @Override
    public void run() {
        System.out.println("passenger "+this.passNum+" thread started");
        while(true) {
            sync.station_wait_for_train(src, this);
            try {Thread.sleep(500);} catch(Exception e){}
        }
    }

    public int getPassNum() {
        return passNum;
    }
    public void setSprite(ImageView sprite){
        this.sprite = sprite;
    }

    public ImageView getSprite(){
        return sprite;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public Station getSrc() {
        return src;
    }

    public void setSrc(Station src) {
        this.src = src;
    }

    public Station getDest() {
        return dest;
    }

    public void setDest(Station dest) {
        this.dest = dest;
    }

    public Train getTrainIn() {
        return trainIn;
    }

    public void setTrainIn(Train trainIn) {
        this.trainIn = trainIn;
    }

    public boolean isBoarded() {
        return boarded;
    }

    public void setBoarded(boolean boarded) {
        this.boarded = boarded;
    }
}
