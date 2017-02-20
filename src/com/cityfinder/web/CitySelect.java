package com.cityfinder.web;

import com.cityfinder.model.ListModule;
import com.cityfinder.web.graph.solution.CitySimilarity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//requestType: string, gets requestType
//city: when request is cityName put in city
//getCityList: make new instance ListModule
//listCity: store list of cities here
//sortCity: new instance of QuickSort
public class CitySelect extends HttpServlet{

    /**
     * Code which actually handles the request.
     * @param request The request to be handled.
     * @param response The response used to write to the client.
     * @throws IOException
     * @throws ServletException
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //System.out.println("Working directory: " + System.getProperty("user.dir")); // Print the working directory

        /* Get parameters */
        String requestType = request.getParameter("reqType"); //gets which type of request
        //search (cityName) or similarity(to a city, cityName), List(no parameter)
        String city = request.getParameter("cityName"); //type cityName
        String method = request.getParameter("method"); // Get the type of request (AJAX or regular)

        /* Set up modules*/
        ListModule lm = new ListModule(); // Fetches list of cities for dropdown
        ArrayList<String[]> wellBeingRecords = QuickSort.sortWellBeingCSV(1); // Holds the split lines of the well being file, sorted alphabetically.
        ArrayList<String[]> crimeIndexRecords = QuickSort.sortCrimeIndexCSV(1); // Holds the split lines of the crime index file, sorted alphabetically.
        CitySimilarity cs = new CitySimilarity(wellBeingRecords, crimeIndexRecords); // Create the module to fetch similar cities, and pass it the data read by quicksort to be read

		/* Set up the response */
        RequestDispatcher view = request.getRequestDispatcher("result.jsp"); // The dispatcher used to forward the request to the JSP

		/* Determine what we need to return */
        switch (requestType) // Determine type of request to respond to
        {
            case "list": {
                ArrayList<String> cityList = lm.getList(); // List of cities
                request.setAttribute("cityList", cityList); // Store the list of cities
                //System.exit(0);
                break;
            }

            case "search":
            {
                /**
                 * NOTE: THE BACK-END ASSUMES THAT THE FRONT-END WILL PRE-FILTER THE LIST OF CITIES SUCH THAT ONLY 1 CITY WILL BE FOUND.
                 */
                //SearchAlgo searchCity = new SearchAlgo();
                String[] citysInfo = SearchAlgo.search(1, city); // An array of strings containing the city's data
                request.setAttribute("citysInfo", citysInfo); // Store array for JSP
                break;
            }

            case "similarity": // Find all cities similar to a given city, with the given weights
            {
                String[] weightsSplit = request.getParameter("scores").split(";"); // Get the list of scores and split them into a string array
                int[] intWeights = new int[weightsSplit.length]; // Create array to hold integer weights

                for (int i = 0; i < weightsSplit.length; i++) // Loop through each string weight
                {
                    intWeights[i] = Integer.parseInt(weightsSplit[i]); // Convert the string to an integer
                }

                request.setAttribute("simCits", cs.simCities(intWeights, city)); // Pass the list of similar cities to the JSP to return
                break;
            }
        }

        /* Set up request data for JSP and forward the request */
        request.setAttribute("method", method); // Store the request method - AJAX or HTTP?
        request.setAttribute("reqType", requestType); // Store the type of request - so that the JSP knows what the results are
        view.forward(request, response); // Send the request and data needed for the view/controller to the JSP

        //writer.write("{\"status\": \"test\", \"requestType\": \"" + requestType + ", \", \"cityName\": \"" + city + "\"}\r\n\r\n"); // Print a JSON response
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response); // Call the request handler function
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response); // Handle the request
    }
}
