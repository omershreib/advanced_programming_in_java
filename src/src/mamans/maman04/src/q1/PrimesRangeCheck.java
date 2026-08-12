package mamans.maman04.src.q1;

import java.util.Arrays;

public class PrimesRangeCheck {

    private final int m;
    private final int n;

    private int nextNumber = 0;

    private final boolean[] results;



    public PrimesRangeCheck(int m, int n) {
        this.m = m;
        this.n = n;

        this.results = new boolean[m+1];
        initResults();
    }

    private void initResults() {
        Arrays.fill(results, true);

        if (results.length > 0)
            results[0] = false;

        if (results.length > 1)
            results[1] = false;
    }

    public synchronized int getNextNumber() {
        nextNumber++;
        return nextNumber;
    }

    public synchronized void setNumberPrimeTestResult(int k, boolean isPrime) {
        if ((0 < k) && (k <= m)) {
            this.results[k] = isPrime;
        }

        else {
            System.out.println("Error: detected an attempt to access results with an index (" + k + ") outside the scope");
        }

    }


    public void printResult() {
        System.out.println("print prime list by order from 1 to " + m + ":");
        for (int i=1; i<=m; i++) {
            if (results[i])
                System.out.println(i);
        }
    }


    public void runCheck() {
        Thread[] workers = new Thread[n];

        for (int i=0; i<n; i++) {
            workers[i] = new Thread(() -> {
                int number;
                boolean result;

                // critical section #1
                while ((number = this.getNextNumber()) <= m) {

                    System.out.println(Thread.currentThread().getName() + " WORK ON " + number);


                    int upperLimit = (int) Math.sqrt(number);

                    for (int k=2; k<=upperLimit;k++) {
                        //System.out.println(number + " % " + k + " = " + (number % k) );
                        if (number % k == 0) {

                            // critical section #2
                            this.setNumberPrimeTestResult(number, false);
                            break;
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " DONE WITH " + number);




                }
            });


            workers[i].setName("Thread-" + (i+1));
            workers[i].start();
        }

        for (Thread worker: workers) {
            try {
                worker.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    }

    public static void main(String[] args) {
        // should be: 2 3 5 7
        PrimesRangeCheck primesRangeCheck = new PrimesRangeCheck(1000, 10);

        primesRangeCheck.runCheck();

        primesRangeCheck.printResult();


    }

}
