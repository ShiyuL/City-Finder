package com.cityfinder.web.graph.datastructures.graph;

import com.cityfinder.web.graph.ancillary.Quick;
import com.cityfinder.web.graph.interfaces.EdgeWeightedDigraphInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Victor on 11-Mar-16.
 *
 * Class representing an edge-weighted digraph of City objects.
 */
public class EdgeWeightedDigraph implements EdgeWeightedDigraphInterface<City> {
    private final int V; // Number of vertices
    private final HashMap<City, Bag<WeightedDirectedEdge>> graphMap; // Hashmap used to find edges of each city. Maps a City to a Princeton Bag of outgoing edges.
    private Quick quick; // Quicksort object used for sorting

    /**
     * Initializes an edge-weighted digraph with V vertices.
     * @param cities An array of the cities in the graph. IT IS ASSUMED THAT THERE ARE NO DUPLICATES!
     */
    public EdgeWeightedDigraph(ArrayList<City> cities) {
        graphMap = new HashMap<City, Bag<WeightedDirectedEdge>>(); // Initialize the hashmap mapping a city to a Princeton Bag containing its outgoing edges.
        V = cities.size(); // Number of cities = # of vertices
        quick = new Quick(); // Create a quicksort class

        for (City c : cities)
        {
            graphMap.put(c, new Bag<WeightedDirectedEdge>()); // Create a new Princeton Bag and associate it with the city
        }

            /*
            Integer values: getInt(), [0, 7]
            String values: getString(), [0, 2].
             */

        for (int intType = 0; intType < 9; intType++) // Sort by each of the integer values and create directed edges in order for each of them
        {
            quick.sortByIntCategory(cities, intType); // Sort the list of cities by the given category
            createEdges(cities, intType); // Create directed edges in sorted order
        }

        for (int strType = 0; strType < 3; strType++) // Sort by each of the String categories and create directed edges in order for each String category
        {
            quick.sortByStringCategory(cities, strType); // Sort the list of cities by the given String category
            createEdges(cities, 9+strType); // Create directed edges in sorted order
        }
    }

    /**
     * Creates directed edges for each city in the array, leading from the previous city to the next.
     * @param c The array of cities to create edges for. IT IS ASSUMED THAT THEY ARE SORTED IN SOME ORDER!!
     * @param catType The category of the edge. Used for shortest-path algorithm.
     */
    private void createEdges(ArrayList<City> c, int catType)
    {
        Bag<WeightedDirectedEdge> edgeList; // List of edges for the current city. Princeton Bag class.

        for (int i = 0; i < c.size() - 1; i++) // Loop through the list of cities
        {
            addEdge(c.get(i), new WeightedDirectedEdge(c.get(i+1), 0, catType, c.get(i))); // Add an edge from this city to the next
        }
    }

    /**
     * This method adds a new weighted, directed edge to the graph. The edge must specify which cities it connects.
     *
     * @param c The city to add the outgoing edge to.
     * @param wde The weighted, directed edge to add.
     */
    public void addEdge(City c, WeightedDirectedEdge wde)
    {
        Bag<WeightedDirectedEdge> b = graphMap.get(c); // Get the city's edgelist. Princeton Bag class.
        b.add(wde); // Add the edge to it
        graphMap.put(c, b); // Store the bag with the new edge added back into the hashmap
    }

    /**
     * Fetches all edges connected to a particular city, or returns null if there aren't any.
     *
     * @param c The city to find the edges of.
     * @return An Iterable containing the adjacent edges.
     */
    @Override
    public Iterable<WeightedDirectedEdge> adj(City c)
    {
        return graphMap.get(c); // Return the Bag containing the adjacency list
    }

    /**
     * Fetches the number of vertices.
     *
     * @return The number of vertices in the graph.
     */
    @Override
    public int V()
    {
        return graphMap.size(); // Return the number of vertices
    }

    /**
     * Fetches all edges in the graph.
     *
     * @return An Iterable containing all edges in the graph.
     */
    @Override
    public Iterable<WeightedDirectedEdge> edges()
    {
        ArrayList<WeightedDirectedEdge> edgeList = new ArrayList<WeightedDirectedEdge>(); // Iterable list of edges to return
        Bag<WeightedDirectedEdge> adjList; // Used to store the edge list for a particular city. Princeton Bag class.

        for (City c : graphMap.keySet()) // Loop through the list of cities
        {
            adjList = graphMap.get(c); // Get the Bag at this location

            for (WeightedDirectedEdge e : adjList) // Loop through the list of edges in this bag
            {
                edgeList.add(e); // Add the edge to the bag
            }
        }

        return edgeList; // Return the list of all edges
    }

    /**
     * Returns an Iterable containing all vertices in the graph.
     * @return An Iterable object containing all vertices in the graph.
     */
    @Override
    public ArrayList<City> vertices()
    {
        ArrayList<City> vs = new ArrayList<City>(); // Create the list to return

        for (City c : graphMap.keySet()) // Loop through the vertices of the graph
        {
            vs.add(c); // Add the city to the list to return
        }

        return vs; // Return the list of vertices
    }

    /**
     * Sets the weight of all edges of the given category to the given weight.
     * @param category The category of the edges to set. Must be a number in the ranges [0, 10]/[0, 11).
     * @param weight The new weight for this type of edge.
     */
    @Override
    public void setCategoryWeight(int category, int weight)
    {
        for (WeightedDirectedEdge wde : edges()) // Loop through the edges in the graph
        {
            /* Check if we have found an edge of the given category */
            if (wde.getCategory() == category) // We have found an edge of the given category
            {
                wde.setMyWeight(weight); // Set the edge's weight appropriately
            }
        }
    }
}
