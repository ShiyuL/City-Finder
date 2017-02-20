package com.cityfinder.web.graph.interfaces;

import com.cityfinder.web.graph.datastructures.graph.City;

import java.util.ArrayList;

/**
 * This interface defines the API for the graph module of the application. This API will be used by the back-end controller to find
 * results.
 */
public interface SimilarityAPI {

    /**
     * Finds all cities "similar" to a given city. Similarity is defined as being within 2 edges of the city node with the given name.
     * @param scoreWeights The list of weights for each type of score.
     * @param cityName The name of the cities to find similar cities to.
     * @return A list of cities which are similar to this one.
     */
    ArrayList<City> simCities(int[] scoreWeights, String cityName);

    /**
     * Finds all cities "similar" to a given city, where "similarity" is defined as being a given # of edges away from a city node with a given
     * name, with the given weights used as edge weightings.
     * @param scoreWeights The weightings for each type of edge.
     * @param cityName The name of the city to find similar cities to.
     * @param simDist The length of the longest paths allowable.
     * @return A list of cities which are similar to the given city.
     */
    ArrayList<City> simCities(int[] scoreWeights, String cityName, int simDist);
}
