package mamans.maman01.src.q2;

import java.util.List;


/**
 * <h3> YearlyAvgTempBackend </h3>
 * <p>
 *     this backend class handle the main "logic" (if we can called it that way) required by this program.
 *     <br> this includes finding the index argument of a temperature list that contains the coldest/hottest value
 *     (the bar charts of the coldest/hottest average temperature must be colored with a different color compared
 *     to the other bar charts)
 * </p>
 *
 * @maman   01
 * @question    2
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-03
 * */

public class YearlyAvgTempBackend {

    /* the default coldest temperature should be higher than any other real temperature
    (so it will be surly overwritten). symmetrically for the hottest temperature */
    private static final double DEFAULT_COLDEST_TEMPERATURE = 999.99;
    private static final double DEFAULT_HOTTEST_TEMPERATURE = -999.99;


    /**
     * this method returns the index argument where a given temperature list (@tempList) contains
     * the hottest temperature value.
     *
     * @param tempList a list of 12 doubles - each depicts the average temperature of its month
     * @return an integer belongs to the index contains the maximal value contained by @tempList
     * */
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

    /**
     * this method returns the index argument where a given temperature list (@tempList) contains
     * the coldest temperature value.
     *
     * @param tempList a list of 12 doubles - each depicts the average temperature of its month
     * @return an integer belongs to the index contains the minimal value contained by @tempList
     * */
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
