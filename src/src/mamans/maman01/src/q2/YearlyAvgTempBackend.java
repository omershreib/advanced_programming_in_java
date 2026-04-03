package mamans.maman01.src.q2;

import java.util.List;

public class YearlyAvgTempBackend {

    /* the default coldest temperature should be higher than any other real temperature
    (so it will be surly overwritten). symmetrically for the hottest temperature */
    private static final double DEFAULT_COLDEST_TEMPERATURE = 999.99;
    private static final double DEFAULT_HOTTEST_TEMPERATURE = -999.99;


    public int getArgOfHottestTemperature(List<Double> tempList) {
        int argHottestTemp = 0;
        double hottestTemp = DEFAULT_HOTTEST_TEMPERATURE;
        double currTemp;

        for (int i=0; i<tempList.size(); i++) {
            currTemp = tempList.get(i);

            if (hottestTemp < currTemp) {
                argHottestTemp = i;
                hottestTemp = currTemp;
            }
        }

        return argHottestTemp;
    }

    public int getArgOfColdestTemperature(List<Double> tempList) {
        int argColdestTemp = 0;
        double ColdestTemp = DEFAULT_COLDEST_TEMPERATURE;
        double currTemp;

        for (int i=0; i<tempList.size(); i++) {
            currTemp = tempList.get(i);

            if (ColdestTemp > currTemp) {
                argColdestTemp = i;
                ColdestTemp = currTemp;
            }
        }

        return argColdestTemp;
    }
}
