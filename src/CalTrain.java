import java.util.ArrayList;

public class CalTrain {
    public ArrayList<String> feedText = new ArrayList<String>();
    Main g;

    public CalTrain(Main g){
        this.g = g;
    }

    public Main getGame(){
        return g;
    }


    public Station station_init(int num) {
//        System.out.println("Initializing Station " + num);
        return new Station(num);
    }

    public void station_load_train(Station station, Train train) {
        int trainExiters;
        if (station.getCurrTrain().getTrainNum() == train.getTrainNum())
        {
            /* Train arrives at specific station */
            //	station.getLock().lock();
            trainExiters = station_off_board(station, train);
            Main.totalPassServed += trainExiters;
            if (station.getStationNum() == 7 && station.getCurrTrain() != null) 				// Last station tapos paright
            {												// Train drops off from one side
//                station.setRightTrain(null);				// and receives in the other
//                station.removeFromQueue(train.getDirection());
//                station.setLeftTrain(train);
//                station.addTrainQueue(train, !train.getDirection());
//                train.setDirection(!train.getDirection());
                station.setCurrTrain(null);
            }
//            else if (station.getStationNum() == 0 && station.getTrain(false) != null) //First station tapos paleft
//            {
//                station.setLeftTrain(null);
//                station.removeFromQueue(train.getDirection());
//                station.setRightTrain(train);
//                station.addTrainQueue(train, !train.getDirection());
//                train.setDirection(!train.getDirection());
//            }

            //NO NEED bec we can get # of seats direcho
//            station.setEmptySeats(train.getDirection(), train.getFreeSeats());
//            station.setTotalSeats(train.getDirection(), train.getNumSeats());

            //station.getLock().unlock();

            System.out.println("Train " + train.getTrainNum() +
                    " arrives in Station " + (station.getStationNum() + 1)
                    + ". Train's number of available seats = "
                    + train.getAvailable());

            /* Boarding: Train side */
//            while(station.getWaitPassCount(train.getDirection()) > 0 && station.getEmptySeats(train.getDirection()) > 0)
            //meron pang waiters & meron pang available
            while(station.getPassWaiting().size() > 0 && station.getCurrTrain().getAvailable() > 0)
            {
                try {
//					station.signalTrain();
//					synchronized(station.getAllPassSeated()){
                    station.waitPassSeated();
//					}

                } catch(Exception e) {}
            }

            System.out.println("Station " + (station.getStationNum() + 1) +
                    " Waiting Passengers - " + station.getPassWaiting().size() +
                    " Empty Seats - " + station.getCurrTrain().getAvailable());

            /* Reset Station */
            //station.getLock().lock();

            //NO NEED: set the empty seats to 0 in station
//            station.setEmptySeats(train.getDirection(), 0);
//            station.setTotalSeats(train.getDirection(), 0);

            System.out.println("Train " + train.getTrainNum()
                    + " leaves Station " + (station.getStationNum() + 1));
//            if(g.t.anims.size() > 0 && train.getTrainNum() - Game.decommissioned >= 0)
//                g.t.getAnim(train.getTrainNum() - Game.decommissioned).start();
            //station.getLock().unlock();
        }
    }

    public void station_wait_for_train(Station station, Passenger pass) {
        /* Passenger arrives at station */
        station.getLock().lock();
        System.out.println("Passenger " + pass.getPassNum() + " arrives at Station "
                + (station.getStationNum() + 1) + ". Destination is Station " +
                (pass.getDest().getStationNum() + 1));

//		feedText.add("Passenger " + pass.getPassNum() + " arrives at Station "
//				   + (station.getStationNum() + 1) + ". Destination is Station " +
//				   (pass.getLeaveStation().getStationNum() + 1));
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
        station.getLock().unlock();

        /* Passenger waits for a train */
//        while (station.getTrainPass(pass.getDirection()) <= station.getEmptySeats(pass.getDirection()))
        while (station.getCurrTrain().getPassBoarded().size() <= station.getCurrTrain().getAvailable())
        {
            try { station.waitTrain(); } catch(Exception e) {}
        }

        station.getLock().lock();
//        if(station.getTrainPass(pass.getDirection()) + 1 < station.getTotalSeats(pass.getDirection()))
        if(station.getCurrTrain().getPassBoarded().size() + 1 < station.getCurrTrain().getCapacity())
        {
//          station.incStandPass(pass.getDirection());
            station.getCurrTrain().addPassBoarded(pass); //IDK IF NEED NA :((
//			System.out.println("Passenger " + pass.getPassNum() +
//							   " boards Train " + station.getTrain(pass.getDirection()).getTrainNum());

            feedText.add("Passenger " + pass.getPassNum() +
                    " boards Train " + station.getCurrTrain().getTrainNum());
        }

        station.getLock().unlock();
    }

    public boolean station_on_board(Station station, Passenger pass, boolean allRode) {
        boolean boarded = false;
        if (station.getCurrTrain() != null)
        {
            System.out.println("station on board! station: "+station.getStationNum()+" passenger: "+pass.getPassNum());

            /* Passenger rides Train and updates Train stuff */
//			System.out.println("Passenger " + pass.getPassNum() + " is on board at Train " +
//				       station.getTrain(pass.getDirection()).getTrainNum());

            System.out.println("Passenger " + pass.getPassNum() + " is on board at Train " +
                    station.getCurrTrain().getTrainNum());

            station.getCurrTrain().addPassBoarded(pass);
            station.getOn_board().lock();
            station.deletePassWaiting(pass.getPassNum());
//            station.decStandPass(pass.getDirection()); //DEC TAKEN WHY??
            station.getCurrTrain().setAvailable(station.getCurrTrain().getAvailable()-1);
            pass.setBoarded(true);
            station.getOn_board().unlock();

            /* Passenger signals station that he/she is seated on the train */
            if (station.getCurrTrain().getAvailable() == 0 ||
                station.getCurrTrain().getPassBoarded().size() == 0 ||
                allRode){
                //WALANG GINAWA
//				synchronized(station.getAllPassSeated()){
//					station.signalPassSeated();
//				}
            }
            boarded = true;
        }
//		try { Thread.sleep(1000); } catch(Exception e) {}
        return boarded;
    }

    public int station_off_board(Station station, Train train) {
        System.out.println("station off board");
        int exiters = 0;
        if (!train.getPassBoarded().isEmpty()) {
            //station.getLock().lock();
            for(int k=0;k<train.getPassBoarded().size();k++) {
                if (train.getPassBoarded().get(k).getDest().getStationNum() == station.getStationNum())
                {
                    station.getCurrTrain().setAvailable(train.getAvailable()-1);

//					System.out.println("Passenger " + t.getRiders().get(k).getPassNum() +
//									   " leaves Train " + t.getTrainNum() +
//									   " at Station " + (station.getStationNum() + 1));

                    feedText.add("Passenger " + train.getPassBoarded().get(k).getPassNum() +
                            " leaves Train " + train.getTrainNum() +
                            " at Station " + (station.getStationNum() + 1));
                    train.deletePassBoarded(train.getPassBoarded().get(k).getPassNum());
                    k--;
                    exiters++;
                }
            }
            //station.getLock().unlock();
//			try { Thread.sleep(500); } catch(Exception e) {}
        }
        return exiters;
    }

    public void countPassengersBoarded(int num) {
        passengersBoarded += num;
    }

    public void countPassengersLeft(int num) {
        passengersLeft -= num;
    }

    public int getPassBoarded() {
        return passengersBoarded;
    }

    public int getPassLeft() {
        return passengersLeft;
    }

    public ArrayList<String> getText(){
        return feedText;
    }

    private int passengersBoarded = 0;
    private int passengersLeft = 10;
    private int threadsCompleted = 10;
}