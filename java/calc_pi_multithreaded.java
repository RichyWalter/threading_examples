import java.lang.System;

public class calc_pi_multithreaded{

    public static void main(String ... args) throws InterruptedException {

        int threadCount = 4;
        int N = 1000000000;

        long startTime = System.currentTimeMillis();

        PiThread[] threads = new PiThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PiThread(threadCount, i, N);
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }
        double pi = 0;
        for (int i = 0; i < threadCount; i++) {
            pi += threads[i].getSum();
        }
        long endTime = System.currentTimeMillis();
        double dur = (endTime-startTime)*0.001; 

        System.out.println("Benoetigte Zeit in Sekunden: " + dur);
        System.out.println("PI/4 = " + pi);
        System.out.println("PI = " + (4*pi));

    }
}