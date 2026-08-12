package mamans.maman04.src.q2;

public class RandomNumber {

    private int lowerBound;

    private int upperBound;

    public RandomNumber(int lowerBound, int upperBound) throws Exception {
        if (lowerBound >= upperBound)
            throw new Exception("the lowerBound must be lower than the upperBound!");

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public double getRandomNumberFromRange() {

        double alpha = Math.random();
        return (alpha * upperBound + (1.0 - alpha) * lowerBound);
    }

}
