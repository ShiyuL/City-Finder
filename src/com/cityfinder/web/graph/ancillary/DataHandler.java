package com.cityfinder.web.graph.ancillary;

/* Java imports */

import com.cityfinder.web.graph.datastructures.graph.City;

import java.util.ArrayList;
import java.util.HashMap;

/* My classes */

/**
 * Abstracts the low-level parsing - handles reading files. Can be changed to read from database instead. Provides an API for other
 * classes to get access to the data.
 */
public class DataHandler
{
    private ArrayList<City> cities; // List of cities loaded from the data
    private HashMap<String, City> citiesByName; // Stores cities by name for fast lookup

    /**
     * Constructor. Calls the various data-loading functions to load the data.
     * @param wellBeingData List of fields for each line in the sorted well-being file.
     * @param crimeData List of fields for each line in the sorted crime data file.
     */
    public DataHandler(ArrayList<String[]> wellBeingData, ArrayList<String[]> crimeData)
    {
        /* Set up vars */
        cities = new ArrayList<City>(); // Create the list of cities
        citiesByName = new HashMap<>(); // Create name->city hashmaps

        /* Load the data */
        loadWellBeingDataFromList(wellBeingData); // TEST: Try loading the well-being data from a list
        loadCrimeDataFromList(crimeData); // TEST: Try loading crime data from the lsit
    }

    /**
     * Loads crime data for a city from the list of strings.
     * @param crimeData The list of fields for each line in the crime file.
     */
    private void loadCrimeDataFromList(ArrayList<String[]> crimeData)
    {
        String[] lSplit; // Holds split line
        String cName; // City's name
        String cProv; // City's province
        City addCity; // The city to which we are adding a crime score

        try
        {
            for (int fLine = 0; fLine < crimeData.size(); fLine++) // Loop through lines in file
            {
                lSplit = crimeData.get(fLine); // The line is already split into fields
                //System.out.println(curLine); // DEBUGGING
                cName = lSplit[1].trim(); // Store the name of the city and remove all leading and trailing spaces
                cProv = lSplit[2].trim(); // Store the city's province and remove all leading and trailing spaces
                //System.out.println("Reading data for " + cName + ", " + cProv);

                /* Check if the city exists */
                if (citiesByName.keySet().contains(cName)) // This city was read from thr well-being index dataset
                {
                    addCity = citiesByName.get(cName); // Fetch the city to which we will add the crime index

                    /* Determine if there is a crime score */
                    if (!lSplit[9].equals("..")) // There is data
                    {
//                        System.out.println("Have crime score " + Double.parseDouble(lSplit[9]) + " for city " + addCity);
                        addCity.setCrimeScore(Double.parseDouble(lSplit[9])); // The last field is the crime index value. Parse it and pass it to the city for storage.

                        /*if (addCity.getIntValue(8) == (int)Math.round(Double.parseDouble(lSplit[9])))
                        {
                            System.out.println("Stored crime score (" + addCity.getIntValue(8) + ") matches parsed value (" + (int)Math.round(Double.parseDouble(lSplit[9])) + ")");
                        }

                        else
                        {
                            //System.out.println("Stored crime score (" + addCity.getIntValue(8) + ") doesn't match parsed value (" + (int)Math.round(Double.parseDouble(lSplit[9])) + ")");
                        }*/
                    } else // No data
                    {
                        //System.out.println("No crime score for city " + addCity);
                        addCity.setCrimeScore(-1); // Use -1 to indicate no data
                        assert addCity.getIntValue(8) == -1;
                    }
                }

                //System.out.println();
            }
        }

        catch (NullPointerException npe) // Null pointer exception
        {
            System.err.println("DataHandler.loadCrimeData: null pointer exception occurred!\nException: " + npe + "\nMessage: " + npe.getMessage());
            System.exit(2);
        }
    }

    /**
     * Loads the well-being data and creates cities using the list passed to the module by QuickSort.
     * @param wellBeingData The list of lines in the sorted file.
     */
    private void loadWellBeingDataFromList(ArrayList<String[]> wellBeingData)
    {
        /* Load data from array of well-being lines */
        int lno = 1; // Line number (used to skip files)
        String[] fields; // Holds list of fields on current line
        int citInd = 0; // Index of city object
        City temp; // Temporary holder for city object

        /* Read the well-being file's data from the ArrayList */
        for (int i = 0; i < wellBeingData.size(); i++) // Loop through the lines parsed by QuickSort
        {
            fields = wellBeingData.get(i); // Get this line's fields
            //System.out.println(curLine);

            for (int f = 0; f < fields.length; f++)
            {
                if (fields[f].equals("")) // Empty string
                {
                    fields[f] = "-1"; // Set it to -1 to mark it as no data
                }
            }

            temp = new City(Integer.parseInt(fields[0]),
                    fields[1],
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4]),
                    Integer.parseInt(fields[5]),
                    Integer.parseInt(fields[6]),
                    parsePercentage(fields[7]),
                    fields[8],
                    fields[9],
                    Integer.parseInt(fields[10])); // Create the new city from this line and store a temporary reference to it
            cities.add(temp); // Add a new city object representing this city to the list
            citiesByName.put(temp.getStrVal(0), temp); // Store city object in hashmap of cities by name for later lookup
            citInd++; // Increment index for next loop
            lno++; // Increment line count
        }
    }

    /**
     * Fetches the list of cities loaded from the datasets.
     * @return The list of cities.
     */
    public ArrayList<City> getCityList()
    {
        return cities;
    }

    /**
     * Parses an integer percentage from a string in the format "[0-9]+%".
     * @param s The string to parse.
     * @return An integer representing the string.
     */
    private int parsePercentage(String s)
    {
        String numStr = s.substring(0, s.length()-1); // A substring from the beginning of the string to one before the percentage - the number
        return Integer.parseInt(numStr); // Return the string as an integer
    }
}