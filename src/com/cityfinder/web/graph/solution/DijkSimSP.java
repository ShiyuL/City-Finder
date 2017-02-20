package com.cityfinder.web.graph.solution;

import com.cityfinder.web.graph.datastructures.IndexMinPQ;
import com.cityfinder.web.graph.datastructures.graph.City;
import com.cityfinder.web.graph.datastructures.graph.EdgeWeightedDigraph;
import com.cityfinder.web.graph.datastructures.graph.WeightedDirectedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Implements a modification of Dijkstra's shortest-path algorithm to find similar cities.
 */
class DijkSimSP
{
    private HashMap<City, WeightedDirectedEdge> edgeTo; // Maps a city to the edge leaving from it
    private HashMap<City, Double> distTo; // Stores the distance to a city
    private IndexMinPQ<Double> pq; // Holds vertices in terms of their distance to each other.
    private ArrayList<City> cityList; // Used to convert a city to an integer for the MinPQ
    private EdgeWeightedDigraph ewd; // Saves the graph for use in allPaths()
    private City source; // The source of the search

    /**
     * Finds all paths leading out from the given city in the given edge-weighted digraph.
     * @param ewd The edge-weighted digraph to search.
     * @param source The city to start the search at.
     */
    DijkSimSP(EdgeWeightedDigraph ewd, City source)
    {
        /* Set up variables */
        edgeTo = new HashMap<City, WeightedDirectedEdge>(); // Create mapping for city to outgoing edge
        distTo = new HashMap<City, Double>(); // Initialize tha mapping of a city to its distance from the given source
        pq = new IndexMinPQ<Double>(ewd.V()); // Create the priority queue to fetch vertices in order of their distance from the source
        cityList = ewd.vertices(); // Fetch the list of vertices
        this.ewd = ewd; // Save a reference to the graph
        this.source = source; // Save a reference to the source city

        for (City c : ewd.vertices()) // Loop through the vertices of the graph
        {
            distTo.put(c, Double.POSITIVE_INFINITY); // Initialize the distance from the source to this node to +inf in preparation for relaxation
        }

        distTo.put(source, 0.0); // The source is 0 <unit>s from itself
        pq.insert(cityList.indexOf(source), 0.0); // Insert the source into the queue with a distance of 0

        while (!pq.isEmpty()) // Loop until all vertices have been visited
        {
            relax(ewd, pq.delMin()); // Relax an edge
        }
    }

    /**
     * Relaxes all edges leaving this vertex if their weights are too high.
     * @param ewd The graph whose edges we are relaxing.
     * @param citInd The index of the city whose edges we will relax.
     */
    private void relax(EdgeWeightedDigraph ewd, int citInd)
    {
        City w; // The vertex to which a directed edge goes
        City v = cityList.get(citInd); // The vertex whose edges we are relaxing

        for (WeightedDirectedEdge wde : ewd.adj(v)) // Loop through the edges adjacent to this vertex
        {
            w = wde.to(); // Store the city connected to the outgoing part of the edge

            if (distTo.get(w) > (distTo.get(v) + wde.weight())) // Only relax if the city is too far away
            {
                distTo.put(w, distTo.get(v) + wde.weight()); // Relax the edge (lower its weight)
                edgeTo.put(w, wde); // Store the edge to find a path later on

                /* Determine how to update the priority queue */
                if (pq.contains(cityList.indexOf(w))) // City is in PQ
                {
                    pq.changeKey(cityList.indexOf(w), distTo.get(w)); // Update the distance to the city
                }

                else // City isn't in PQ
                {
                    pq.insert(cityList.indexOf(w), distTo.get(w)); // Add the city to the PQ
                }
            }
        }
    }

    /**
     * Determines if there is a path from the given source to the given city.
     * @param c The city to find the path to.
     * @return True if there is a path to the given city from the given source, false if there isn't.
     */
    boolean hasPathTo(City c)
    {
        return distTo.get(c) < Double.POSITIVE_INFINITY; // The city's distance must have been lowered if there is a path to it
    }

    /**
     * Finds the path to the given destination from the source given at class creation, if one exists.
     * @param c The city to find a path to.
     * @return Null if no path exists, an Iterable containing the edges on the path in reverse order otherwise.
     */
    Stack<City> pathTo(City c)
    {
        /* There has to be a path to this city */
        if (!hasPathTo(c)) // No path
        {
            return null; // Let caller know
        }

        Stack<City> path = new Stack<City>(); // Create the stack to store the edges
        WeightedDirectedEdge copy = null; // Copy of the edge. Used to fetch the first city on the path

        for (WeightedDirectedEdge wde = edgeTo.get(c); wde != null; wde = edgeTo.get(wde.from())) // Loop backwards through edgeTo from destination
        {
            path.push(wde.to()); // Add the city to the path
            copy = wde; // Save a copy of the edge. Used to save the last edge.
        }

        path.push(copy.from()); // Push the source city (the city from which the final edge came)

        return path; // Return the path
    }

    /**
     * Returns a list of all paths to all vertices reachable from the given source.
     * @return An ArrayList containing all paths to all vertices reachable from the given source.
     */
    ArrayList<Stack<City>> allPaths()
    {
        ArrayList<Stack<City>> pathsList = new ArrayList<>(); // Create the list used to store the paths

        for (City c : cityList) // Loop through the graph's vertices
        {
            if (!c.equals(source)) // If this city isn't the source
            {
                /* Try to find a path to it */
                if (hasPathTo(c)) // There is a path to this city
                {
                    pathsList.add(pathTo(c)); // Add the path to this city to the list
                }
            }
        }

        return pathsList; // Return the list of all paths leaving from the source
    }
}