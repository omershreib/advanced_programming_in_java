package mamans.maman04.src.q2;

import java.util.Arrays;
import java.util.Objects;

public class Airport {
    private String name;

    private int runways;

    private String[] runwayPool;

    private final int NO_FREE_RUNWAY_STATUS_CODE = -1;
    private final String FREE_RUNWAY = null;


    public String getName() {
        return name;
    }

    private String getAirportMessagePrefix() {
        return "Airport " + name + ": ";
    }

    public Airport(String name, int runways) {
        this.name = name;
        this.runways = runways;

        this.runwayPool = new String[runways];

        Arrays.fill(runwayPool, FREE_RUNWAY);
    }


    private int allocateNextFreeRunway(String flightNumber) {
        for (int i=0; i<runways; i++) {
            if (Objects.equals(runwayPool[i], FREE_RUNWAY)) {
                runwayPool[i] = flightNumber;
                return i;
            }

        }

        return NO_FREE_RUNWAY_STATUS_CODE;
    }



    public synchronized int allocateRunway(String flightNumber) throws InterruptedException {
        int nextRunway;

        String flightString = "flight #" + flightNumber;

        System.out.println(getAirportMessagePrefix() + flightString + " request a free runway");

        while ((nextRunway = allocateNextFreeRunway(flightNumber)) == NO_FREE_RUNWAY_STATUS_CODE) {
            System.out.println(getAirportMessagePrefix() + "airport is too busy, cannot allocate a runway for " + flightString);
            wait();
        }

        System.out.println(getAirportMessagePrefix() + "allocate runway #" + nextRunway + " to " + flightString);

        return nextRunway;
    }

    public int depart(String flightNumber) throws InterruptedException {
        return allocateRunway(flightNumber);

    }

    public int land(String flightNumber) throws InterruptedException {
        return allocateRunway(flightNumber);
    }

    public synchronized void freeRunway(int runway) {
        System.out.println(getAirportMessagePrefix() + "free runway #" + runway);
        runwayPool[runway] = FREE_RUNWAY;

        notifyAll();
    }

}
