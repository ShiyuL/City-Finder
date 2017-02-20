package com.cityfinder.web.graph.datastructures.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Victor on 13-Apr-16.
 *
 * Class which tests the EdgeWeightedDigraph class.
 */
public class EdgeWeightedDigraphTest {
    /* Test digraphs */
    private static EdgeWeightedDigraph addEdgesEWD; // Test digraph for adding edges
    private static EdgeWeightedDigraph adjEWD; // Digraph for adjacency test

    /* Test lists of cities */
    private static ArrayList<City> addEdgesList; // List of cities used for adding edges test
    private static ArrayList<City> adjList; // List of cities for adjacency test

    private static City temp; // Used to hold City objects temporarily

    /**
     * Sets up the graph for testing.
     */
    @BeforeClass
    public static void setupBeforeClass()
    {
        /* Set up add edge test */
        addEdgesList = new ArrayList<>();
        temp = new City(1, "a", 2, 3, 4, 5, 6, 7, "b", "c", 7);
        temp.setCrimeScore(8);
        addEdgesList.add(temp);
        temp = new City(9, "hi", 10, 11, 12, 13, 14, 15, "ON", "as", 16);
        temp.setCrimeScore(5);
        addEdgesList.add(temp);
        temp = new City(9, "hi", 10, 11, 12, 13, 14, 15, "ON", "as", 16);
        temp.setCrimeScore(5);
        addEdgesList.add(temp);
        addEdgesEWD = new EdgeWeightedDigraph(addEdgesList); // Create digraph for adding edges

        /* Set up adjacency test */
        adjList = new ArrayList<>();
        temp = new City(1, "a", 2, 3, 4, 5, 6, 7, "b", "c", 7);
        temp.setCrimeScore(8);
        adjList.add(temp);
        temp = new City(9, "hi", 10, 11, 12, 13, 14, 15, "ON", "as", 16);
        temp.setCrimeScore(5);
        adjList.add(temp);
        temp = new City(9, "hi", 10, 11, 12, 13, 14, 15, "ON", "as", 16);
        temp.setCrimeScore(5);
        adjList.add(temp);
        adjEWD = new EdgeWeightedDigraph(addEdgesList); // Create digraph for adding edges
    }

    /**
     * Tests adding an edge to the graph.
     * @throws Exception
     */
    @Test
    public void addEdge() throws Exception {
        City addEdgeCit = new City(17, "sdf", 18, 19, 20, 21, 22, 23, "MN", "sdf", 3556); // Test city so that we can guarantee that a duplicate edge doesn't exist
        WeightedDirectedEdge testEdge = new WeightedDirectedEdge(addEdgeCit, 0, 3, addEdgesList.get(0)); // Test edge to add
        boolean edgeWasAdded = false; // Whether or not the new edge was added

        addEdgesEWD.addEdge(addEdgesList.get(0), testEdge); // Try adding an edge from this city to the next one

        for (WeightedDirectedEdge wde : addEdgesEWD.edges()) // Loop through the list of edges in the graph
        {
            if (wde.equals(testEdge)) // The edge was added
            {
                edgeWasAdded = true; // Save that fact
                break; // No need to search further
            }
        }

        assertTrue(edgeWasAdded); // The edge must have been added
    }

    @Test
    public void adj() throws Exception {
        Iterable<WeightedDirectedEdge> adjEdges = adjEWD.adj(adjList.get(0)); // Edges going out from first city

        for (WeightedDirectedEdge wde : adjEdges) // Loop through edges
        {

        }
    }

    @Test
    public void v() throws Exception {

    }

    @Test
    public void edges() throws Exception {

    }

    @Test
    public void vertices() throws Exception {

    }

    @Test
    public void setCategoryWeight() throws Exception {

    }

}