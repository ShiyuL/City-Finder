package com.cityfinder.web;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SearchAlgo {//Searches according to the specific criteria

	/**
	 *
	 * @param args - takes user input if applicable
	 * @throws FileNotFoundException - throw file not found exception
	 * @throws UnsupportedEncodingException - throw unsupported encoding exception
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// 1 = alphabetically by city name
		// 2 = income
		// 3 = education
		// 4 = housing
		// 5 = labour force activity
		// 6 = well-being
		// 7 = crime rate
		// 8 = over-all score
		search(1,"Whitecourt");//Search by city name
/*		search(2,55);//Search by income
		search(3,55);//Search by education
		search(4,55);//Search by housing
		search(5,55);//Search by labour
		search(6,55);//Search by well-being
		search(7,55);//Search by crime rate
		search(5,55);//Search by over-all score
		*/
		for (String ele:search(1,"Whitecourt")){
			System.out.print(ele + " ");
		}
		//System.out.println(search(1,"Whitecourt"));
	}

	/**
	 * This method searches through the list of cities using a specific criteria and turns the city along with all the information it contains
	 * @param sortType - flag to indicate which criteria is used  searched
	 * @param criteria - the specific requirements for the criteria
	 * @return - returns an arrayList containing the information
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static String [] search(int sortType, String criteria) throws FileNotFoundException, UnsupportedEncodingException  {
		//int count =0;

		ArrayList<String[]>  data = QuickSort.sortWellBeingCSV(sortType);	//Load the Canada well being arrayList which is pre-sorted by city name
/*			for (String info: data.get(3249)){
				System.out.println(info);
				count ++;
			}
			System.out.println("--------------------------------------------");*/

		int bRes = binarySearch(data,criteria);

		if (bRes != -1) {
			return data.get(bRes);
		}
		else
		{
			return null;
		}
	}

	/**
	 *
	 * @param data - takes in an arryList of data
	 * @param criteria - takes in the specific requirement for the criteria
	 * @return
	 */
	private static int binarySearch(ArrayList<String[]> data, String criteria) {
	        int lo = 0;
	        int hi = data.size() - 1;
	        //Use binary search to find the city that satisfy the specific requirement and return its index
	        while (lo <= hi) {
	            // Key is in a[lo..hi] or not present.
	            int mid = lo + (hi - lo) / 2;
	            if      (criteria.compareTo(data.get(mid)[1]) < 0) hi = mid - 1;
	            else if (criteria.compareTo(data.get(mid)[1]) > 0 )  lo = mid + 1;
	            else return mid;
	    }
		return -1;
	}


}

//search by cityname
//search by criteria
//return information
//jacen