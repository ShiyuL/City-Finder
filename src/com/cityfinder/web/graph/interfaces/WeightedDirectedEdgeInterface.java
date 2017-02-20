package com.cityfinder.web.graph.interfaces;

/**
 * Created by Victor on 11-Mar-16.
 *
 * Interface which a directed edge class must implement.
 */
public interface WeightedDirectedEdgeInterface<T> {
    T to(); // Where does this edge go to?
    double weight(); // The weight of this edge
    T from(); // Where does this edge leave from?
}

