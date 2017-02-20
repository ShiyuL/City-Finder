package com.cityfinder.web.graph.datastructures.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Victor on 13-Apr-16.
 *
 * This class tests the City class.
 */
public class CityTest {
    private static City test;
    private static int[] iVals = {1, 2, 3, 4, 5, 6, 7, 8}; // Test integer vals
    private static String[] sVals = {"Wichita", "KP","hye"}; // Test str vals

    /**
     * Sets up variables used in all tests.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        test = new City(iVals[0], sVals[0], iVals[1], iVals[2], iVals[3], iVals[4], iVals[5], iVals[6], sVals[1], sVals[2], iVals[7]); // Create test city with test data
    }

    /**
     * Ensures that crime score is set accurately.
     * @throws Exception
     */
    @Test
    public void setCrimeScore() throws Exception {
        test.setCrimeScore(9); // Use a known crime score
        assertEquals("Crime scores aren't equal!", test.getIntValue(8), 9);
    }

    @Test
    public void getIntValue() throws Exception {
        for (int i = 0; i < 8; i++) // Loop through int vals (except crime score)
        {
            assertEquals("Error in int test for category " + i, iVals[i], test.getIntValue(i));
        }
    }

    @Test
    public void getStrVal() throws Exception {
        for (int i = 0; i < 3; i++) // Loop through str vals
        {
            assertEquals("Error in str test for category " + i, sVals[i], test.getStrVal(i));
        }
    }

    @Test
    public void testToString() throws Exception {
        test.setCrimeScore(16);
        assertEquals("toStr error", test.toString(), "{\"csdCode\": " + Integer.toString(iVals[0]) + ", \"csdName\": \"" + sVals[0] + "\", \"income\": " + Integer.toString(iVals[1]) + ", \"edScore\": " + Integer.toString(iVals[2]) + ", \"houScore\": " + Integer.toString(iVals[3]) + ", \"labActScore\": " + Integer.toString(iVals[4]) + ", \"cwbScore\": " + Integer.toString(iVals[5]) + ", \"globNonRespPerc\": " + Integer.toString(iVals[6]) + ", \"prov\": \"" + sVals[1] + "\", \"pop\": " + Integer.toString(iVals[7]) + ", \"crimeScore\": " + Integer.toString(16) + "}");
    }

}