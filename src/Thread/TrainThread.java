package Thread;
import Model.Train;

public class TrainThread extends RunnableThread {
    public TrainThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //add logic
        System.out.println("current time of execution: "+System.currentTimeMillis() % 1000);
    }
}