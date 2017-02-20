package com.example.web;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

	private ArrayList<String[]> cities;

	@Before
	public void setUp() throws FileNotFoundException, UnsupportedEncodingException {
		cities = new ArrayList<String[]>();
	}

	@Test
	public void testWellBeingSort() throws FileNotFoundException, UnsupportedEncodingException {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			cities = QuickSort.sortWellBeingCSV(i);
			assertTrue(QuickSort.isSorted(cities, 0, cities.size() - 1, i, "well being"));
		}
	}

	@Test
	public void testCrimeSort() throws FileNotFoundException, UnsupportedEncodingException {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			cities = QuickSort.sortCrimeIndexCSV(i);
			assertTrue(QuickSort.isSorted(cities, 0, cities.size() - 1, i, "crime index"));
		}
	}
}
