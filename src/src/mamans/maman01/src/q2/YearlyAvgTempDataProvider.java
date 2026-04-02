package mamans.maman01.src.q2;

import java.util.*;

public class YearlyAvgTempDataProvider {
    private final Map<Integer, List<Double>> yearToMonthlyAvgTemps = new HashMap<>();

    private boolean isInitialized = false;


    public boolean getIsInitialized() {
        return isInitialized;
    }

    public int getMaxYear() {
        return Collections.max(yearToMonthlyAvgTemps.keySet());
    }

    public int getMinYear() {
        return Collections.min(yearToMonthlyAvgTemps.keySet());
    }

    public boolean isYearInData(int year) {
        return yearToMonthlyAvgTemps.containsKey(year);
    }

    public List<Double> getYearlyData(int year) {
        if (yearToMonthlyAvgTemps.containsKey(year)) {
            return yearToMonthlyAvgTemps.get(year);
        }

        System.out.println("error: cannot provide data for this year: " + Integer.toString(year));
        return null;
    }

    public List<Integer> getAllYearsKeys() {
        return this.yearToMonthlyAvgTemps.keySet().stream().toList();
    }

    public void init() {

        if (!isInitialized) {
            yearToMonthlyAvgTemps.put(2021, List.of(16.2, 15.98, 16.92, 19.11, 23.40, 25.20, 28.40, 29.26, 27.50, 24.70, 21.87, 16.62));

            yearToMonthlyAvgTemps.put(2022, List.of(13.37, 14.88, 14.37, 19.44, 21.81, 25.62, 27.64, 28.44, 27.42, 25.01, 21.23, 17.85));

            yearToMonthlyAvgTemps.put(2023, List.of(16.28, 14.46, 17.93, 19.35, 22.16, 25.04, 28.08, 28.78, 27.96, 26.0, 22.37, 18.99));

            yearToMonthlyAvgTemps.put(2024, List.of(16.05, 15.77, 18.20, 21.63, 23.25, 27.08, 29.36, 29.38, 27.96, 24.57, 20.39, 17.45));

            yearToMonthlyAvgTemps.put(2025, List.of(16.95, 14.70, 18.63, 20.65, 23.11, 25.81, 28.62, 29.33, 27.91, 25.26, 23.83, 18.02));

            //yearToMonthlyAvgTemps.put(2026, List.of(16.91, 18.56, 17.15, null, null, null, null, null, null, null, null, null));

            isInitialized = true;
        }

    }

}
