import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// SEARCH PATTERN from https://www.w3schools.com/java/java_regex.asp


public class Airport {
    // declaring fields of the Airport class
    private int airportID;
    private String airportName, city, country;


    /**
     * This instantiates an airport object
     * @param airportID the ID of the airport
     * @param name the airportName of the airport
     * @param city the city where the airport is located
     * @param country the country the airport is in
     */
    public Airport(int airportID, String name, String city,
                   String country) {
        this.airportID = airportID;
        this.airportName = name;
        this.city = city;
        this.country = country;
    }

    /**
     * This function returns the airportID of the airport
     *
     * @return The airportID is being returned.
     */
    public int getAirportID() {
        return airportID;
    }

    /**
     * This function returns the airportName of the airport.
     *
     * @return The airportName of the person.
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * This function returns the city of the address
     *
     * @return The city variable is being returned.
     */
    public String getCity() {
        return city;
    }

    /**
     * This function returns the country of the user
     *
     * @return The country variable is being returned.
     */
    public String getCountry() {
        return country;
    }

    public static boolean isBlank(String data) {
        if(data.isBlank() || data == null || data.length() == 0) {
            return true;
        }
        else {
            for (int i = 0; i < data.length(); i++) {
                if(!Character.isWhitespace(data.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function reads the airport file and returns a HashMap of airport codes and their corresponding locations
     *
     * @return A HashMap of airport locations.
     * @throws FileNotFoundException throws an exception when the file is not found
     */
    public static HashMap<String, ArrayList<String>> readAirportFile() throws FileNotFoundException {
        File airportInfo = new File("airports.csv");
        Scanner scanner = new Scanner(airportInfo);

        HashMap<String, ArrayList<String>> airportLocation = new HashMap<>();

        while (scanner.hasNextLine()) {
            // search pattern to aid in finding data
            final String SEARCH_PATTERN = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

            String fileData = scanner.nextLine();
            String[] fileDateStorage = fileData.split(SEARCH_PATTERN, -1);

            if (fileDateStorage[4].equalsIgnoreCase("\\N")) {
                continue;
            }

            String code = fileDateStorage[4]; // gets the airport code
            String cityAndCountry = fileDateStorage[2] + ',' + fileDateStorage[3];
            airportLocation.putIfAbsent(cityAndCountry, new ArrayList<>());
            airportLocation.get(cityAndCountry).add(code);
        }
        scanner.close();
        return airportLocation;
    }
}


