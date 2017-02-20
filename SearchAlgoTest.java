package com.example.web;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchAlgoTest {
	static ArrayList<String[]>  data = new ArrayList <String[]>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		data = QuickSort.sortWellBeingCSV(1);	//Load the Canada well being arrayList which is pre-sorted by city name

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void test() throws FileNotFoundException, UnsupportedEncodingException {
		String[] case1 = SearchAlgo.search(1, "Whitecourt");
		String[] case2=  SearchAlgo.search(1, "Abbotsford");
		String[] case3 = SearchAlgo.search(1, "Toronto");
		String[] case4 = SearchAlgo.search(1, "Hamilton");
		String[] case5 = SearchAlgo.search(1, "Mono");
		String[] case6 = SearchAlgo.search(1, "Mississauga");
		String[] case7 = SearchAlgo.search(1, "Piikani 147");
		String[] case8 = SearchAlgo.search(1, "Rudy No. 284");
		String[] case9 = SearchAlgo.search(1, "Saint-Joseph");
		String[] case10 = SearchAlgo.search(1, "Sunset Point");

		for (int i=0; i<11;i++) {
		assertTrue(case1[i].equals(data.get(3249)[i]));
		assertTrue(case2[i].equals(data.get(1)[i]));
		assertTrue(case3[i].equals(data.get(3057)[i]));
		assertTrue(case4[i].equals(data.get(1011)[i]));
		assertTrue(case5[i].equals(data.get(1655)[i]));
		assertTrue(case6[i].equals(data.get(1644)[i]));
		assertTrue(case7[i].equals(data.get(2013)[i]));
		assertTrue(case8[i].equals(data.get(2247)[i]));
		assertTrue(case9[i].equals(data.get(2448)[i]));
		assertTrue(case10[i].equals(data.get(2967)[i]));
		
		}
	}

}
