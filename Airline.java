import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Airline {
    // declaring fields of the Airline class
    private int airportID;
    private String airportName;
    private String country;

    /**
     * This instantiates an airline
     * @param airportID the airport ID
     * @param country the country the airline is located in
     */
    public Airline(int airportID, String airportName, String country) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.country = country;
    }

    /**
     * This function returns the country the airline is in
     *
     * @return The country variable is being returned.
     */
    public String getCountry() {
        return country;
    }

}
