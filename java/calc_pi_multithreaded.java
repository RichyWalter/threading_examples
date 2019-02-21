public class calc_pi_multithreaded{

    public static void main(String ... args) throws InterruptedException {

        int threadCount = 4;
        int N = 100_000;
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
        System.out.print("PI/4 = " + pi);
        System.out.print("\nPI = " + (4*pi));

    }
}