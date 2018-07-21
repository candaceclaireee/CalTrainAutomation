package Thread;
import Model.Passenger;

public class PassengerThread extends RunnableThread {
    public PassengerThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //System.out.println("current time of execution: "+System.currentTimeMillis() % 1000);

    }

}


