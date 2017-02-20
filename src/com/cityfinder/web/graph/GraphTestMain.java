package com.cityfinder.web.graph;

import com.cityfinder.web.QuickSort;
import com.cityfinder.web.graph.datastructures.graph.City;
import com.cityfinder.web.graph.solution.CitySimilarity;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Victor on 11-Mar-16.
 *
 * The main class. Tests the graph.
 */
public class GraphTestMain {

    /**
     * Main method of program. Called by OS.
     * @param args Command-line args (unused).
     */
    public static void main(String[] args)
    {
        try {
            CitySimilarity cs = new CitySimilarity(QuickSort.sortWellBeingCSV(1), QuickSort.sortCrimeIndexCSV(1)); // Create a city similarity object to query
            int[] weights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10}; // Use test weights
            String testName = "Hamilton";
            ArrayList<City> sCs = cs.simCities(weights, testName); // Try to find similar cities

        /* Error check */
            if (sCs != null) // No errors
            {
                System.out.println("["); // Start the array

                for (int i = 0; i < sCs.size(); i++) {
                    System.out.println(sCs.get(i) + ","); // Print the city
                }

                System.out.println("]"); // End the array
            } else // Errors detected
            {
                System.out.println("Error: the city \"" + testName + "\" doesn't exist.");
            }
        }

        catch (FileNotFoundException fnfe)
        {
            System.out.println("GraphTestMain: couldn't find file.\nException: " + fnfe + "\nException message: " + fnfe.getMessage());
        }

        catch (UnsupportedEncodingException uee)
        {
            System.out.println("GraphTestMain: unsupported encoding exception occured.\nException: " + uee + "\nException message: " + uee.getMessage());
        }
    }
}
