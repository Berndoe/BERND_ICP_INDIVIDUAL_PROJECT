import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Route {
//   declaring fields of the Route class
    private final String airlineCode;
    private final String destinationAirportCode;

    private final String startAirport;
    private int stops;

    /**
     * This constructor is used to instantiate a route
     * @param airlineCode the code of the airline
     * @param destinationAirportCode the code of the destination Airport
     * @param stops the number of stops taken to get to the destination
     */
    public Route(String airlineCode, String startAirport, String destinationAirportCode, int stops) {
        this.airlineCode = airlineCode;
        this.startAirport = startAirport;
        this.destinationAirportCode = destinationAirportCode;
        this.stops = stops;
    }

    /**
     * This function returns the airline code of the flight
     *
     * @return The airline code.
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * This function returns the destination airport code
     *
     * @return The destination airport code.
     */
    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    /**
     * This function returns the startAirport variable
     *
     * @return The startAirport variable is being returned.
     */
    public String getStartAirport() {
        return startAirport;
    }

    /**
     * This function returns the number of stops.
     *
     * @return The number of stops.
     */
    public int getStops() {
        return stops;
    }

    /**
     * Given a list of airplane routes, return a hashmap of the routes for each airplane
     */
    public static HashMap<String, ArrayList<Route>> airplaneRoutes() throws FileNotFoundException {
        File routeInfo = new File("routes.csv");
        Scanner scanner = new Scanner(routeInfo);
        HashMap<String, ArrayList<Route>> airlineCountry = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] routeData = scanner.nextLine().split(",");

            String airlineCode = routeData[0];
            String startAirportCode = routeData[2];
            String endAirportCode = routeData[4];
            int stopNumber = Integer.parseInt(routeData[7]);

            Route route = new Route(airlineCode, startAirportCode, endAirportCode, stopNumber);

            airlineCountry.putIfAbsent(startAirportCode, new ArrayList<>());
            airlineCountry.get(startAirportCode).add(route);
        }
        scanner.close();
        return airlineCountry;
    }
}
