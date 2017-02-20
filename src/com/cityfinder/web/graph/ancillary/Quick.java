package com.cityfinder.web.graph.ancillary;

import com.cityfinder.web.graph.datastructures.graph.City;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Victor on 11-Mar-16.
 *
 * A class implementing quicksort and its private methods.
 */
public class Quick {
    /**
     * Sorts a list of cities by one of their integer categories.
     * @param c The list of cities to sort.
     * @param intType The number of the integer category to sort by. Used to fetch the values for comparison.
     */
    public void sortByIntCategory(ArrayList<City> c, int intType)
    {
        shuffle(c); // Shuffle the list of cities randomly to eliminate dependence on input
        sortByIntCategory(c, 0, c.size() - 1, intType); // Sort the array
    }

    /**
     * Sorts a list of cities by one of their string categories.
     * @param c The list of cities to sort.
     * @param strType The number of the string category to sort by. Used to fetch the values for comparison.
     */
    public void sortByStringCategory(ArrayList<City> c, int strType)
    {
        shuffle(c); // Shuffle the list of cities randomly to eliminate dependence on input
        sortByStringCategory(c, 0, c.size() - 1, strType); // Sort the array
    }

    /**
     * Sorts the ArrayList of cities by the given integer category.
     * @param c The ArrayList of cities to sort.
     * @param lo The lower bound of the sort.
     * @param hi The upper bound of the sort.
     * @param intType The ID of the integer category to sort by.
     */
    private void sortByIntCategory(ArrayList<City> c, int lo, int hi, int intType)
    {
        if (hi <= lo)
        {
            return; // End the recursion.
        }

        int j = partitionByIntCategory(c, lo, hi, intType); // Find the index of the partitioning com.cityfinder.web.graph.datastructures.graph.City
        sortByIntCategory(c, lo, j - 1, intType); // Recursively sort the left half of the array
        sortByIntCategory(c, j + 1, hi, intType); // Recursively sort the right half of the array
    }

    /**
     * Sorts the ArrayList of cities by the given string category.
     * @param c The ArrayList of cities to sort.
     * @param lo The lower bound of the sort.
     * @param hi The upper bound of the sort.
     * @param intType The ID of the string category to sort by.
     */
    private void sortByStringCategory(ArrayList<City> c, int lo, int hi, int intType)
    {
        if (hi <= lo)
        {
            return; // End the recursion.
        }

        int j = partitionByStringCategory(c, lo, hi, intType); // Find the index of the partitioning com.cityfinder.web.graph.datastructures.graph.City
        sortByStringCategory(c, lo, j - 1, intType); // Recursively sort the left half of the array
        sortByStringCategory(c, j + 1, hi, intType); // Recursively sort the right half of the array
    }

    /**
     * Partitions the array into low and high halves, using the integer category given by intType.
     * @param a The array to partition.
     * @param lo The lower bound of the partition.
     * @param hi The upper bound of the partition.
     * @param intType The ID of the integer to sort by.
     */
    private int partitionByIntCategory(ArrayList<City> a, int lo, int hi, int intType)
    {
        int i = lo, j = hi; // Left and right scan indices
        City v = a.get(lo); // Partitioning item

        while (true)
        { // Scan right, scan left, check for scan complete, and exchange
            while (intCategoryLess(a.get(++i), v, intType))
            {
                if (i == hi)
                {
                    break;
                }
            }

            while (intCategoryLess(v, a.get(--j), intType))
            {
                if (j == lo)
                {
                    break;
                }
            }

            if (i >= j)
            {
                break;
            }

            exch(a, i, j); // Exchange the cities
        }

        exch(a, lo, j); // Put partitioning item v into a[i]
        return j; // Partitioning index
    }

    /**
     * Partitions the array into low and high halves, using the string category given by strType.
     * @param a The array to partition.
     * @param lo The lower bound of the partition.
     * @param hi The upper bound of the partition.
     * @param strType The ID of the string to sort by.
     */
    private int partitionByStringCategory(ArrayList<City> a, int lo, int hi, int strType)
    {
        int i = lo, j = hi; // Left and right scan indices
        City v = a.get(lo); // Partitioning item

        while (true)
        { // Scan right, scan left, check for scan complete, and exchange
            while (stringCategoryLess(a.get(++i), v, strType))
            {
                if (i == hi)
                {
                    break;
                }
            }

            while (stringCategoryLess(v, a.get(--j), strType))
            {
                if (j == lo)
                {
                    break;
                }
            }

            if (i >= j)
            {
                break;
            }

            exch(a, i, j); // Exchange the cities
        }

        exch(a, lo, j); // Put partitioning item v into a[i]
        return j; // Partitioning index
    }

    /**
     * Swaps the values at indA and indB in ArrayList a.
     * @param a The ArrayList to swap values in.
     * @param indA The index of the first element to swap.
     * @param indB The index of the second element to swap.
     */
    private void exch(ArrayList<City> a, int indA, int indB)
    {
        City temp = a.get(indA);
        a.set(indA, a.get(indB)); // Copy a[indB] to a[indA]
        a.set(indB, temp); // Copy old value of a[indA] to a[indB]
    }

    /**
     * Determines whether city a is < city b in the given integer category.
     * @param a The first city to compare.
     * @param b The second city to compare.
     * @param cat The integer category index.
     * @return True if cat value for city a < cat value for city b.
     */
    private boolean intCategoryLess(City a, City b, int cat)
    {
        return a.getIntValue(cat) < b.getIntValue(cat); // Check if the category value for city a < category value for city b
    }

    /**
     * Determines whether city a is < city b in the given string category.
     * @param a The first city to compare.
     * @param b The second city to compare.
     * @param cat The string category index.
     * @return True if cat value for city a < cat value for city b.
     */
    private boolean stringCategoryLess(City a, City b, int cat)
    {
        return a.getStrVal(cat).compareTo(b.getStrVal(cat)) < 0; // Check if the category value for city a < category value for city b
    }

    /**
     * Shuffles an ArrayList of cities to put them in random order.
     * @param c The list of cities to shuffle.
     */
    private void shuffle(ArrayList<City> c)
    {
        Random rand = new Random(); // RNG used for swaps
        int newInd; // New index of item being swapped

        for (int i = 0; i < c.size(); i++) // Loop through the list
        {
            newInd = rand.nextInt(c.size()); // Choose a random index in the range [0, c.size())
            exch(c, i, newInd); // Exchange the values at i and newInd
        }
    }
}