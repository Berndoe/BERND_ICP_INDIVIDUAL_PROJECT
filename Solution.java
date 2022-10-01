import java.io.*;
import java.util.*;

public class Solution {
    private static HashMap<String, ArrayList<Route>> flightPath;
    private static HashMap<String, ArrayList<String>> airports;
    private static ArrayList<String> destinationAirportsList;
    private static ArrayList<String> startAirportsList;
    private static String goalAirport;
    private static String startAirport;

    // static is used to encompass the try catch as the variables used are static
    static {
        try {
            flightPath = Route.airplaneRoutes();
            airports = Airport.readAirportFile();
        }
        catch (FileNotFoundException fne) {
            throw new RuntimeException();
        }
    }

    /**
     * If the destination airport is in the list of airports, then return true if the destination point is in the list of
     * destination airports for that airport
     *
     * @param destinationPoint The destination point that the user has entered.
     * @return The method is returning a boolean value.
     */
    public static boolean finalDestination(String destinationPoint) {
        destinationAirportsList = airports.get(goalAirport);
        return destinationAirportsList.contains(destinationPoint);
    }

    /**
     * The function takes in a starting city and country and returns a list of all possible flight paths from the starting
     * city to the destination city
     *
     * @param startCityAndCountry The city and country of the starting airport.
     */
    public static ArrayList<String> displayFlightPath(String startCityAndCountry) {
        ArrayList<String> accessibleAirports = airports.get(startCityAndCountry);
        Queue<Node> frontier = new LinkedList<>();
        HashSet<String> uniqueStates = new HashSet<>(); // keeps track of unique states

        for (int i = 0; i < accessibleAirports.size(); i++) {
            Node startState = new Node(accessibleAirports.get(i));
            frontier.add(startState);
            System.out.println("access: " +startState.toString());

            if(finalDestination(accessibleAirports.get(i))) {
                startState.flightPath();
            }
        }

        while (!(frontier.isEmpty())) {
            Node currentAirport = frontier.remove();
            uniqueStates.add(currentAirport.getNodeState());

            if (!flightPath.containsKey(currentAirport.getNodeState())) {
                continue;
            }

            ArrayList<Route> successorStates = flightPath.get(currentAirport.getNodeState());

            for (int i = 0; i < successorStates.size(); i++) {
                String destinationAirportCode = successorStates.get(i).getDestinationAirportCode();
                String airlineCode = successorStates.get(i).getAirlineCode();
                int numberOfStops = successorStates.get(i).getStops();

                Node child = new Node(destinationAirportCode, airlineCode, (numberOfStops + 1), currentAirport);
                System.out.println("access2 : " +child.toString());
                if (!(uniqueStates.contains(child.getNodeState()) && frontier.contains(child))) {
                    if (finalDestination(child.getNodeState())) {
                       return child.flightPath();
                    }
                    frontier.add(child);
                }

            }
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {

        try {
            File inputFile = new File("trial-input.txt");
            Scanner scanner = new Scanner(inputFile);
            startAirport = scanner.nextLine();
            goalAirport = scanner.nextLine();
            scanner.close();
        }
        catch (FileNotFoundException fne) {
            System.out.println(fne.getMessage());
        }


        displayFlightPath(startAirport);

    }










}