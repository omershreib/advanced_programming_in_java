package mamans.maman01.src.q2;

import java.util.List;

public class YearlyAvgTempBackend {

    public int getArgOfHottestTemperature(List<Double> tempList) {
        int argHottestTemp = 0;
        double hottestTemp = -999.99;
        double currTemp;


        for (int i=0; i<12; i++) {
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
        double ColdestTemp = 999.99;
        double currTemp;


        for (int i=0; i<12; i++) {
            currTemp = tempList.get(i);

            if (ColdestTemp > currTemp) {
                argColdestTemp = i;
                ColdestTemp = currTemp;
            }
        }

        return argColdestTemp;
    }
}
