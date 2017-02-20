package com.cityfinder.model;

import java.util.ArrayList;

public class ListModule {
    public ArrayList<String> getList(){
        ArrayList<String> cities = new ArrayList<String>();

        /* Debugging - use a controlled list for now */
        cities.add("Toronto");
        cities.add("Mississauga");
        cities.add("Oshawa");
        cities.add("Hamilton");
        cities.add("Vancouver");
        cities.add("Yellowknife");
        cities.add("Calgary");
        cities.add("Edmonton");
        cities.add("Winnipeg");
        cities.add("Charlottetown");
        cities.add("Iqaluit");
        cities.add("Belleville");
        cities.add("Thunder Bay");
        return cities;
    }

//	public List getBrands(String color) {
//		List brands = new ArrayList();
//		if (color.equals("amber")) {
//			brands.add("Jack Amber");
//			brands.add("Red Moose");
//		} else {
//			brands.add("Jail Pale Ale");
//			brands.add("Gout Stout");
//		}
//		return brands;
//	}

}