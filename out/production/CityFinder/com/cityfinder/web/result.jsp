<%@ page import="java.util.*" %>
<%@ page import="com.cityfinder.web.graph.datastructures.graph.City" %>

<%
    try
    {
    /* Response setup */
    response.setCharacterEncoding("UTF-8");

    String method = (String) request.getAttribute("method");  // Store the method used - AJAX or HTTP
    String reqType = (String) request.getAttribute("reqType"); // Get the type of request. Used to determine what the data is.

    switch (reqType) // Determine what variables we need to output based on the request type
    {
        case "list": // Front-end controller is requesting a list of cities for the dropdown
        {
            ArrayList<String> cityList = (ArrayList<String>) request.getAttribute("cityList"); // Get the list of cities returned by ListModule

            if (method.equalsIgnoreCase("ajax")) // Fetching data via AJAX, print JSON response
            {
                response.setContentType("application/json"); // Set content type appropriately

                out.print("["); // Start the JSON array

                for (int i = 0; i < cityList.size()-1; i++) // Loop through all but they last city
                {
                    out.print("\"" + cityList.get(i) + "\", ");
                }

                out.print("\"" + cityList.get(cityList.size() - 1) + "\"]"); // Print the last city and close the JSON array
            }

            else // Not ajax, print list
            {
                response.setContentType("text/html"); // Set content type appropriately

               out.print("<html><head><title>List of Cities</title><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" /></head><body><ol>"); // Start an ordered list

               for (int i = 0; i < cityList.size()-1; i++) // Loop through all but they last city
               {
                    out.print("<li>" + cityList.get(i) + "</li>"); // Print the list's name as one element
               }

               out.print("</ol></body></html>"); // End the ordered list and the HTML page
            }

            break;
        }

        case "search": // Front-end controller is requesting the back-end to perform a search and return results
        {
            String[] citInfo = (String[]) request.getAttribute("citysInfo"); // Get the data returned by the search

            /* Generate different types of output for AJAX and regular HTTP requests */

            if (method.equalsIgnoreCase("ajax")) // AJAX request
            {
                /** Return the search result as JSON **/
                response.setContentType("application/json"); // Set the content type appropriately

                /* Check for errors */
                if (citInfo.length == 1 && citInfo[0].equals("nomatch")) // No match found
                {
                    out.print("{error: true, eType: \"" + citInfo[0] + "\"}"); // Print a single object letting
                }

                else // Found a match
                {
                    out.print("{\"csdCode\": " + Integer.parseInt(citInfo[0]) + ", \"censusSubDiv\": \"" + citInfo[1] + "\", \"income\": " + Integer.parseInt(citInfo[2]) + ", \"education\": " + Integer.parseInt(citInfo[3]) + ", \"housing\": " + Integer.parseInt(citInfo[4]) + ", \"labour\": " + Integer.parseInt(citInfo[5]) + ", \"CWB\": " + Integer.parseInt(citInfo[6]) + ", \"prov\": \"" + citInfo[8] + "\", \"pop\": " + Integer.parseInt(citInfo[10]) + "}"); // Print the data in JSON
                }
            }

            else // Regular HTTP request
            {
                response.setContentType("text/html"); // Set the content type appropriately

                /* Check for errors */
                if (citInfo.length == 1 && citInfo[0].equals("nomatch")) // No match found
                {
                    out.print("<h1>No match found</h1>"); // Print a single object letting
                }

                else // Found a match
                {
                    out.print("<div>Census subdivision code: " + Integer.parseInt(citInfo[0]) + "<br/> Census subdivision: " + citInfo[1] + "<br />Income: " + Integer.parseInt(citInfo[2]) + "<br />Education: " + Integer.parseInt(citInfo[3]) + "<br />Housing: " + Integer.parseInt(citInfo[4]) + "<br />Labour: " + Integer.parseInt(citInfo[5]) + "<br />CWB: " + Integer.parseInt(citInfo[6]) + "<br />Province: " + citInfo[8] + "<br />Population: " + Integer.parseInt(citInfo[10])); // Print the data in JSON
                }
            }

            break;
        }

        case "similarity": // Return list of similar cities
        {
            ArrayList<City> simCits = (ArrayList<City>)request.getAttribute("simCits"); // Fetch the list of similar cities

            /* Determine how to print response */
            if (method.equalsIgnoreCase("ajax")) // AJAX, print JSON list
            {
                response.setContentType("application/json");

                out.print("["); // Start the list

                for (int i = 0; i < simCits.size(); i++) // Loop through the indices
                {
                    if (i != simCits.size() - 1) // Not last city, print comma
                    {
                        out.print("\"" + simCits.get(i).getStrVal(0) + "\", "); // Print the city
                    }

                    else // Last city, don't print comma
                    {
                        out.print("\"" + simCits.get(i).getStrVal(0) + "\""); // Print the city
                    }
                }

                out.print("]"); // Close the list
            }

            else // HTTP, print data
            {
                response.setContentType("text/html");

                out.print("<ol>"); // Start the list

                for (int i = 0; i < simCits.size(); i++) // Loop through the indices
                {
                    out.print("<li>" + simCits.get(i) + "</li>"); // Print the city
                }

                out.print("</ol>"); // Close the list
            }

            break;
        }
    }
    }

    catch (NumberFormatException nfe)
    {
        out.println("Number format exception occurred: " + nfe);
    }
%>