package com.cityfinder.web.graph.solution;

import com.cityfinder.web.graph.ancillary.DataHandler;
import com.cityfinder.web.graph.datastructures.graph.City;
import com.cityfinder.web.graph.datastructures.graph.EdgeWeightedDigraph;
import com.cityfinder.web.graph.interfaces.SimilarityAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/* Types for Bag */

/**
 * This class exposes the main graph API to the rest of the application.
 *
 * It allows the rest of the application to find similar cities using a simple API.
 */
public class CitySimilarity implements SimilarityAPI {
    private EdgeWeightedDigraph ewd; // Edge-weighted digraph used to determine city similarity
    private ArrayList<City> cities; // Array of cities used to pass to graph constructor.
    private DataHandler dh; // Class used to abstract away the details of reading the data. Reads in data in constructor and creates list of cities.
    private HashMap<String, City> citiesByName; // HashMap used to easily find a city, given it's name

    /***
     * Constructor. Loads in the data and creates the graph.
     * @param wellBeingRecords The sorted data from the well-being data file.
     * @param crimeIndexRecords The sorted data from the crime index data file.
     */
    public CitySimilarity(ArrayList<String[]> wellBeingRecords, ArrayList<String[]> crimeIndexRecords)
    {
        /* Initialize variables */
        cities = new ArrayList<City>(); // Create the ArrayList
        citiesByName = new HashMap<String, City>(); // Initialize the HashMap

        /** Load data **/
        dh = new DataHandler(wellBeingRecords, crimeIndexRecords); // Data handler to abstract away the parsing process (reads in data in constructor).
        cities = dh.getCityList(); // Get the list of cities from the DataHandler

        /* Add cities to name HashMap for ease of retrieval */
        for (City c : cities) // Loop through the list of cities
        {
            citiesByName.put(c.getStrVal(0), c); // Associate the city with its name
        }

        /* Create the graph */
        ewd = new EdgeWeightedDigraph(cities); // Create the graph of cities
    }

    /**
     *  Finds all cities "similar" to a given city. Similarity is defined as being within 2 edges of the city node with the given name.
     * @param scoreWeights The list of weights for each type of score.
     * @param cityName The name of the cities to find similar cities to.
     * @return A list of cities which are similar to this one.
     */
    @Override
    public ArrayList<City> simCities(int[] scoreWeights, String cityName) {
        if (citiesByName.keySet().contains(cityName)) // If this city is in the dataset
        {
            return simCities(scoreWeights, cityName, 2); // Use a similarity distance of 2
        }

        else // Error
        {
            return null;
        }
    }

    /**
     * Finds all cities "similar" to a given city, where "similarity" is defined as being a given # of edges away from a city node with a given
     * name, with the given weights used as edge weightings.
     * @param scoreWeights The weightings for each type of edge.
     * @param cityName The name of the city to find similar cities to.
     * @param simDist The length of the longest paths allowable.
     * @return A list of cities which are similar to the given city.
     */
    @Override
    public ArrayList<City> simCities(int[] scoreWeights, String cityName, int simDist)
    {
        DijkSimSP dsp; // Task class implementing the modified Dijkstra path-finding algorithm
        ArrayList<City> similarCities = new ArrayList<City>(); // Create a list to hold the similar cities

        System.out.println("Finding cities similar to \"" + cityName + "\" (" + citiesByName.get(cityName) + ")");

        /* Set the weights appropriately */

        for (int i = 0; i < scoreWeights.length; i++) // The index tells us which score the weight is associated with.
        {
            /* Prevent division by zero */
            if (scoreWeights[i] != 0) // Not a zero weight
            {
                ewd.setCategoryWeight(i, 1 / scoreWeights[i]); // Tell the graph to set the weights of the given edge type (index) to 1/the given weight - the higher the user's weighting, the more cities they want to find with similar values in that category.
            }

            else // Zero weight
            {
                ewd.setCategoryWeight(i, 10); // 0 means the user doesn't care about this category. We need to invert this to make the weight so large that
                                            // Dijkstra's algorithm won't want to use edges of this category. Thus, we set the weight to the maximum
                                            // weight.
            }
        }

        /* Find all paths of length simDist leading out from the given city */
        dsp = new DijkSimSP(ewd, citiesByName.get(cityName)); // Create a Dijkstra task class to find all paths leading out from this city

        for (Stack<City> path : dsp.allPaths()) // Loop through all paths leaving this city
        {
            /* Check if it's length falls within the acceptable range */
            if (path.size() <= simDist && path.size() >= 1) // The length of the path to this destination was within the acceptable range - [1, simDist]
            {
                /* Add the unique cities on this path to the list of similar cities */
                for (City c : path) // Loop through cities on tha path
                {
                    if (!similarCities.contains(c) && !c.equals(citiesByName.get(cityName))) // If this isn't the source, and this isn't a duplicate...
                    {
                        similarCities.add(c); // Add it to the list of similar cities
                    }
                }
            }
        }

        return similarCities; // Return the list of similar cities
    }
}
