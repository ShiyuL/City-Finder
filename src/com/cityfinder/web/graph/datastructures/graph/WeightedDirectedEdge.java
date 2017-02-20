package com.cityfinder.web.graph.datastructures.graph;

import com.cityfinder.web.graph.interfaces.WeightedDirectedEdgeInterface;

/**
 * Created by Victor on 11-Mar-16.
 *
 * This class implements a weighted and directed edge in an edge-wweighted DAG. It also has an edge type, as the cities are connected
 * by edges of different types.
 */
public class WeightedDirectedEdge implements WeightedDirectedEdgeInterface<City> {
    private final City toCity; // The city which this edge goes to.
    private int myWeight; // The weight of this edge
    private final int myCategory; // Type of edge this is
    private City fromCity; // The city from which this edge leaves

    /**
     * Creates a weighted and directed edge between cities
     * @param to The city which this edge goes to.
     * @param w The weight of this edge.
     * @param catType The category of this edge (city name, csd code, etc.)
     * @param from The city from which this edge leaves.
     */
    public WeightedDirectedEdge(City to, int w, int catType, City from)
    {
        /* Save variables */
        toCity = to; // Save the source
        myWeight = w; // Save the weight
        myCategory = catType; // Save the edge's category.
        fromCity = from; // Save the city from which this edge leaves
    }

    /**
     * Fetches the city from which this edge leaves.
     * @return The city from which this edge leaves.
     */
    public City from()
    {
        return fromCity; // Return the city
    }

    /**
     * Fetch the city which this edge goes to.
     * @return The city which this edge goes to.
     */
    public City to()
    {
        return toCity;
    }

    /**
     * Fetches the weight of this edge.
     * @return This edge's weight.
     */
    public double weight()
    {
        return myWeight;
    }

    /**
     * Sets this edge's weight to the given weight.
     * @param newWeight The new weight of the edge.
     */
    public void setMyWeight(int newWeight)
    {
        myWeight = newWeight; // Store the new weight
    }

    /**
     * Fetches this edge's category.
     * @return An integer representing the category of this edge. The integer will be in the range [0, 11).
     */
    public int getCategory()
    {
        return myCategory; // Return this edge's category
    }
}