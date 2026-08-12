package mamans.maman04.src.q2;


/*
* flight workflow:
* ---------------
*
* call Airport.depart() / Airport.land()
* if runway available
* then:
*   1. simulate()
*   2. signal Airport to release runway
* else:
*   wait()
*
* */

public class Flight extends Thread {

    private String flightNumber;

    private Airport depatingAirport;

    private Airport landingAirport;

    private final int MIN_DEPART_TIME = 2;
    private final int MAX_DEPART_TIME = 5;
    private final int MIN_LAND_TIME = 2;
    private final int MAX_LAND_TIME = 5;

    private final int MIN_FLIGHT_TIME = 10;
    private final int MAX_FLIGHT_TIME = 20;


    public Flight(String flightNumber, Airport from, Airport to) {
        this.flightNumber = flightNumber;
        this.depatingAirport = from;
        this.landingAirport = to;
    }


    private String getFlightMessagePrefix() {
        return "Flight #" + flightNumber + ": ";
    }

    private static long toSecond(long milliseconds) {
        return milliseconds * 1000;
    }

    private void simulateRunwayUsage(boolean isDeparture,String airportName, int runway, int minTime, int maxTime)
            throws Exception {


        String action = (isDeparture) ? "departing from" : "landing to";
        String suffix = action + " " + airportName + " runway #" + runway;


        RandomNumber randomNumber = new RandomNumber(minTime, maxTime);
        long simulationTime = (long) randomNumber.getRandomNumberFromRange();

        System.out.println(getFlightMessagePrefix() + "start " + suffix);

        Thread.sleep(toSecond(simulationTime));

        System.out.println(getFlightMessagePrefix() + "end " + suffix);

    }

    private void simulateFlight() throws Exception {

        RandomNumber randomNumber = new RandomNumber(MIN_FLIGHT_TIME, MAX_FLIGHT_TIME);
        long simulationTime = (long) randomNumber.getRandomNumberFromRange();

        System.out.println(getFlightMessagePrefix() + "exits its departing airport airspace - will arrived designation in "
                + simulationTime + " seconds");

        Thread.sleep(toSecond(simulationTime));

        System.out.println(getFlightMessagePrefix() + "enter its landing airport airspace - preparing to land");

    }

    private int requestRunway(boolean isDeparture) throws Exception {
        return isDeparture? depatingAirport.depart(this.flightNumber) : landingAirport.land(this.flightNumber);
    }

    private void releaseRunway(boolean isDeparture, int runway) {

        String prefix = "signal airport ";
        String suffix = " that runway #" + runway + " is now free";
        if (isDeparture) {
            System.out.println(getFlightMessagePrefix() + prefix + this.depatingAirport.getName() + suffix);
            depatingAirport.freeRunway(runway);
        }
        else {
            System.out.println(getFlightMessagePrefix() + prefix + this.landingAirport.getName() + suffix);
            landingAirport.freeRunway(runway);
        }
    }

    public String getFlightDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("flightNumber #");
        stringBuilder.append(flightNumber);
        stringBuilder.append(" from ");
        stringBuilder.append(depatingAirport.getName());
        stringBuilder.append(" to ");
        stringBuilder.append(landingAirport.getName());

        return stringBuilder.toString();
    }

    @Override
    public void run() {
        super.run();

        int runway;

        try {
            System.out.println(getFlightMessagePrefix() + "begins flight");

            runway = requestRunway(true);
            simulateRunwayUsage(true, depatingAirport.getName(), runway, MIN_DEPART_TIME, MAX_DEPART_TIME);
            releaseRunway(true, runway);

            simulateFlight();

            runway = requestRunway(false);
            simulateRunwayUsage(true, landingAirport.getName(), runway, MIN_LAND_TIME, MAX_LAND_TIME);
            releaseRunway(false, runway);

            System.out.println(getFlightMessagePrefix() + "ends flight");
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }


}
