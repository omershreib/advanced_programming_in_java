package mamans.maman01.src.q2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h3> YearlyAvgTempDataProvider </h3>
 * <p>
 *     this class implements a temperature data archive (using hashMap), so by giving a year:
 *     <br>
 *     dataProvider(year) → monthly-average-temp-values of that year
 *     <br>
 *     <b>important!</b> the init() method must called once before using this class getters methods
 *
 * </p>
 *
 * @maman   01
 * @question    2
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-03
 * */

public class YearlyAvgTempDataProvider {
    private final Map<Integer, List<Double>> yearToMonthlyAvgTemps = new HashMap<>();
    private boolean isInitialized = false;


    public boolean getIsInitialized() {
        return isInitialized;
    }

    /**
     * return the newest (maximum) year with a temperature data contained by this dataProvider
     *
     * @return an integer (newest year)
     * */
    public int getMaxYear() {
        return Collections.max(yearToMonthlyAvgTemps.keySet());
    }


    /**
     * return the oldest (minimum) year with a temperature data contained by this dataProvider
     *
     * @return an integer (oldest year)
     * */
    public int getMinYear() {
        return Collections.min(yearToMonthlyAvgTemps.keySet());
    }

    public boolean isYearInData(int year) {
        return yearToMonthlyAvgTemps.containsKey(year);
    }

    /**
     * yearGetter
     * <br>
     * if @year is not exist then return null
     *
     * @param year an integer
     * @return a list of 12 doubles - each depicts the average temperature of its month
     * (or null, following a given year that this dataProvider does not have data on)
     * */
    public List<Double> getYearlyData(int year) {
        if (yearToMonthlyAvgTemps.containsKey(year))
            return yearToMonthlyAvgTemps.get(year);

        System.out.println("error: cannot provide data for this year: " + year);
        return null;
    }

    /**
     * return all years supported as keys by this dataProvider class
     *
     * @return a list of integers: [2021, 2022, 2023, 2024, 2025]
     * */
    public List<Integer> getAllYearsKeys() { return new ArrayList<>(this.yearToMonthlyAvgTemps.keySet()); }

    /**
     * this initializing method create this monthly-average-temperature per year data archive. this by filling
     * this hashMap archive with temperature data for these following 5 years between
     * 2021 and 2025.
     * <br> this is a real data by the way, taken from Israel's "Tel Aviv Coast" meteorological station unit.
     * <br> as a safe mechanism, I used the isInitialized flag to make sure that this data archive is set only once,
     * even if by a mistake or any reason this init() method will be called more the once.
     * */
    public void init() {

        if (!isInitialized) {

            yearToMonthlyAvgTemps.put(2021, new ArrayList<>(Arrays.asList(16.2, 15.98, 16.92, 19.11, 23.40, 25.20, 28.40, 29.26, 27.50, 24.70, 21.87, 16.62)));

            yearToMonthlyAvgTemps.put(2022, new ArrayList<>(Arrays.asList(13.37, 14.88, 14.37, 19.44, 21.81, 25.62, 27.64, 28.44, 27.42, 25.01, 21.23, 17.85)));

            yearToMonthlyAvgTemps.put(2023, new ArrayList<>(Arrays.asList(16.28, 14.46, 17.93, 19.35, 22.16, 25.04, 28.08, 28.78, 27.96, 26.0, 22.37, 18.99)));

            yearToMonthlyAvgTemps.put(2024, new ArrayList<>(Arrays.asList(16.05, 15.77, 18.20, 21.63, 23.25, 27.08, 29.36, 29.38, 27.96, 24.57, 20.39, 17.45)));

            yearToMonthlyAvgTemps.put(2025, new ArrayList<>(Arrays.asList(16.95, 14.70, 18.63, 20.65, 23.11, 25.81, 28.62, 29.33, 27.91, 25.26, 23.83, 18.02)));

            isInitialized = true;

       /* must cleaner, but does not supported by java 8    :(
        if (!isInitialized) {
            yearToMonthlyAvgTemps.put(2021, List.of(16.2, 15.98, 16.92, 19.11, 23.40, 25.20, 28.40, 29.26, 27.50, 24.70, 21.87, 16.62));

            yearToMonthlyAvgTemps.put(2022, List.of(13.37, 14.88, 14.37, 19.44, 21.81, 25.62, 27.64, 28.44, 27.42, 25.01, 21.23, 17.85));

            yearToMonthlyAvgTemps.put(2023, List.of(16.28, 14.46, 17.93, 19.35, 22.16, 25.04, 28.08, 28.78, 27.96, 26.0, 22.37, 18.99));

            yearToMonthlyAvgTemps.put(2024, List.of(16.05, 15.77, 18.20, 21.63, 23.25, 27.08, 29.36, 29.38, 27.96, 24.57, 20.39, 17.45));

            yearToMonthlyAvgTemps.put(2025, List.of(16.95, 14.70, 18.63, 20.65, 23.11, 25.81, 28.62, 29.33, 27.91, 25.26, 23.83, 18.02));

            //yearToMonthlyAvgTemps.put(2026, List.of(16.91, 18.56, 17.15, null, null, null, null, null, null, null, null, null));
        */

        }

    }

}
