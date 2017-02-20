package com.cityfinder.web.graph.ancillary;

import com.cityfinder.web.QuickSort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Victor on 13-Apr-16.
 *
 * This class tests the data abstraction class.
 */
public class DataHandlerTest {
    private DataHandler dh; // Object used to test class

    @Before
    public void setUp() throws Exception {
        dh = new DataHandler(QuickSort.sortWellBeingCSV(1), QuickSort.sortCrimeIndexCSV(1)); // Create the DataHandler which will be tested
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests if all cities were loaded from the dataSet.
     * @throws Exception
     */
    @Test
    public void getCityList() throws Exception {
        assertEquals("DataHandlerTest: fewer rows than expected!", dh.getCityList().size(), 3329); // Should have as many rows as well-being file
    }

}