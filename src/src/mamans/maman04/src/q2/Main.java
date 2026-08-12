package mamans.maman04.src.q2;


public class Main {

    public static void main(String[] args) {


        int numberOfFlights = 10;
        int numberOfRunways = 3;

        Airport tlvAirport = new Airport("Tel-Aviv", numberOfRunways);
        Airport romAirport = new Airport("Rome", numberOfRunways);

        Flight flights[] = new Flight[numberOfFlights];

        boolean toRome;

        for (int i=0; i<numberOfFlights; i++) {

            toRome = CoinFlip.run();

            flights[i] = toRome?
                    new Flight(RandomFlightNumber.generateFlightNumber(), tlvAirport, romAirport) :
                    new Flight(RandomFlightNumber.generateFlightNumber(), romAirport, tlvAirport);

        }

        // display flight "schedule" (by order)
        for (Flight flight : flights) {
            System.out.println(flight.getFlightDetails());
        }


        for (Flight flight : flights) {
            flight.start();
        }

    }

}
