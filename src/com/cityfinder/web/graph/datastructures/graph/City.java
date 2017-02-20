package com.cityfinder.web.graph.datastructures.graph;

public class City {
    /* See constructor for details */
    private int myCSD;
    private String myCSDName;
    private int myIncome;
    private int myHousingScore;
    private int myEdScore;
    private int myLabourActivityScore;
    private int myCWBScore;
    private int myGlobNonRespPerc;
    private String myProv;
    private String myCollectivityType;
    private int myPop; // City's population
    private int crimeScore; // Rounded crime score

    /**
     * Sets this city's crime score.
     * @param cScore The score to use.
     */
    public void setCrimeScore(double cScore)
    {
        crimeScore = (int) Math.round(cScore);
    }

    /**
     * Fetches one of the integer values associated with the city.
     * @param valNum The value's ID (detailed in the method body).
     * @return The value, if it exists, or -2 otherwise.
     */
    public int getIntValue(int valNum)
    {
        int toReturn; // Value to return

        switch (valNum) // Determine what to return based on the value
        {
            case 0: // CSD
            {
                toReturn = myCSD;
                break;
            }

            case 1: // Income
            {
                toReturn = myIncome;
                break;
            }

            case 2: // Housing score
            {
                toReturn = myHousingScore;
                break;
            }

            case 3: // Education score
            {
                toReturn = myEdScore;
                break;
            }

            case 4: // Labour activity score
            {
                toReturn = myLabourActivityScore;
                break;
            }

            case 5: // CWB score
            {
                toReturn = myCWBScore;
                break;
            }

            case 6: // Non-response percentage
            {
                toReturn = myGlobNonRespPerc;
                break;
            }

            case 7: // Population
            {
                toReturn =  myPop;
                break;
            }

            case 8: // Crime score
            {
                toReturn = crimeScore;
                break;
            }

            default: // Unknown variable
            {
                toReturn = -2;
                break;
            }
        }

        return toReturn; // Return the value
    }

    /**
     * Fetches the string value associated with the value number.
     * @param valNum The number of the value to return.
     * @return The string value, or an empty string if it doesn't exist.
     */
    public String getStrVal(int valNum)
    {
        String toReturn;

        switch (valNum)
        {
            case 0: // Name
            {
                toReturn = myCSDName;
                break;
            }

            case 1: // Province
            {
                toReturn = myProv;
                break;
            }

            case 2: // Collectivity type
            {
                toReturn = myCollectivityType;
                break;
            }

            default: // Error
            {
                toReturn = "";
                break;
            }
        }

        return toReturn;
    }

    /**
     * Constructor. Creates a city object from the provided data.
     * @param csdCode The CSD (census subdivision) code of the city.
     * @param csdName The city's census subdivision name.
     * @param income The average. income in the city.
     * @param edScore The city's education score.
     * @param housScore The city's housing score.
     * @param labAct The city's labour activity score.
     * @param cwbScore The city's CWB score.
     * @param gnr The city's global non-response percentage.
     * @param prov The city's province.
     * @param collec The city's collectivity type.
     * @param pop The city's population.
     */
    public City(int csdCode, String csdName, int income, int edScore, int housScore, int labAct, int cwbScore, int gnr, String prov, String collec, int pop)
    {
            /* Store constructor vars */
        myCSD = csdCode;
        myCSDName = csdName != null ? csdName : ""; // Prevent null str values - check if it is null and assign conditionally
        myIncome = income;
        myEdScore = edScore;
        myHousingScore = housScore;
        myLabourActivityScore = labAct;
        myCWBScore = cwbScore;
        myGlobNonRespPerc = gnr;
        myProv = prov != null ? prov : "";
        myCollectivityType = collec != null ? collec : "";
        myPop = pop;
    }

    /**
     * Represents a city as a string containing all of the city's variables.
     * @return A string representation of the city.
     */
    public String toString()
    {
        return "{\"csdCode\": " + Integer.toString(myCSD) + ", \"csdName\": \"" + myCSDName + "\", \"income\": " + Integer.toString(myIncome) + ", \"edScore\": " + Integer.toString(myEdScore) + ", \"houScore\": " + Integer.toString(myHousingScore) + ", \"labActScore\": " + Integer.toString(myLabourActivityScore) + ", \"cwbScore\": " + Integer.toString(myCWBScore) + ", \"globNonRespPerc\": " + Integer.toString(myGlobNonRespPerc) + ", \"prov\": \"" + myProv + "\", \"pop\": " + Integer.toString(myPop) + ", \"crimeScore\": " + Integer.toString(crimeScore) + "}";
    }
}