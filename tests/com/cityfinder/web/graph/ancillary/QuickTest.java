package com.cityfinder.web.graph.ancillary;

import com.cityfinder.web.QuickSort;
import com.cityfinder.web.graph.datastructures.graph.City;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Victor on 13-Apr-16.
 *
 * This class tests my custom Quicksort class.
 */
public class QuickTest {
    private static DataHandler dh; // DataHandler for list of cities
    private static ArrayList<City> cityList;
    private static Quick quick;

    /**
     * Sets up variables used in all tests.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        dh = new DataHandler(QuickSort.sortWellBeingCSV(1), QuickSort.sortCrimeIndexCSV(1)); // Create DataHandler used to fetch cities to sort
        quick = new Quick(); // Quicksort class to tests
        cityList = dh.getCityList();
    }

    /**
     * Sets up each test by fetching the list of cities again.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sortByIntCategory() throws Exception {
        for (int cat = 0; cat < 9; cat++) // Loop through categories of integer data
        {
            shuffle(cityList); // Shuffle the list to ensure it is unsorteds
            quick.sortByIntCategory(cityList, cat); // Sort list by current category
            assertTrue("Sorting by int cat produced unsorted array!", intIsSorted(cityList, cat)); // Ensure that the list is sorted
        }
    }

    @Test
    public void sortByStringCategory() throws Exception {
        for (int cat = 0; cat < 3; cat++) // Loop through categories of integer data
        {
            shuffle(cityList); // Shuffle the list to ensure it is unsorteds
            quick.sortByStringCategory(cityList, cat); // Sort list by current category
            assertTrue("Sorting by int cat produced unsorted array!", strIsSorted(cityList, cat)); // Ensure that the list is sorted
        }
    }

    private boolean intIsSorted(ArrayList<City> cs, int iCat)
    {
        for (int i = 0; i < cs.size()-1; i++)
        {
            if (!(cs.get(i).getIntValue(iCat) < cs.get(i+1).getIntValue(iCat))) // This city's value isn't less than the next one
            {
                System.out.println("cs[" + i + "][" + iCat + "] = " + cs.get(i).getIntValue(iCat) + "\ncs[" + (i+1) + "][" + iCat + "] = " + cs.get(i+1).getIntValue(iCat));
                return false; // Not sorted
            }
        }

        return true; // If we got here, array is sorted
    }

    private boolean strIsSorted(ArrayList<City> cs, int iCat)
    {
        for (int i = 0; i < cs.size()-1; i++)
        {
            if (!(cs.get(i).getStrVal(iCat).compareTo(cs.get(i+1).getStrVal(iCat)) < 0)) // This city's value isn't less than the next one
            {
                System.out.println("cs[" + i + "][" + iCat + "] = " + cs.get(i).getStrVal(iCat) + "\ncs[" + (i+1) + "][" + iCat + "] = " + cs.get(i+1).getStrVal(iCat));
                return false; // Not sorted
            }
        }

        return true; // If we got here, array is sorted
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