package com.cityfinder.web.graph.datastructures.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Victor on 13-Apr-16.
 *
 * Class testing the Princeton Bag class.
 */
public class BagTest {
    /* Princeton Bag classes for tests */
    private static Bag<Integer> iBag; // Bag holding integers for tests
    private static Bag<Integer> eBag; // Empty bag to test isEmpty
    private static Bag<Integer> aBag; // Bag used to test add()

    /**
     * Sets up Bag for tests.
     */
    @BeforeClass
    public static void beforeClass()
    {
        iBag = new Bag<Integer>(); // Create the bag of integers
        eBag = new Bag<>(); // Create the empty bag
        aBag = new Bag<>(); // Create the sBag used to test add()

        for (int i = 0; i < 10; i++) // Fill test integer bag
        {
            iBag.add(i);
        }
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(eBag.isEmpty()); // Bag w/ no items added should be empty
        assertFalse(iBag.isEmpty()); // Bag w/ items shouldn't be empty
    }

    @Test
    public void size() throws Exception {
        assertEquals("Bag's size is incorrect!", iBag.size(), 10);
    }

    @Test
    public void add() throws Exception {
        boolean containsFive = false; // Whether or not bag contains 5 (it should)

        aBag.add(5);

        for (int i : aBag) // Loop through bag
        {
            if (i == 5)
            {
                containsFive = true;
                break;
            }
        }

        assertTrue(containsFive);
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> it = iBag.iterator();
        boolean[] sawNum = new  boolean[10]; // Boolean array to check if we have seen all of the numbers which we put in

        for (int i = 0; i < 10; i++)
        {
            assertTrue(it.hasNext());
            sawNum[it.next()] = true; // Mark this # as seen
        }

        for (int i = 0; i < 10; i++) // Every # should have been seen
        {
            assertTrue(sawNum[i]);
        }
    }

}