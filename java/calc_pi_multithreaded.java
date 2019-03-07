import java.lang.System;
import java.util.Scanner;

public class calc_pi_multithreaded{

    public static void main(String ... args) throws InterruptedException {

        int threadCount = 4;
        int N = getInput();      //1000000000;

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

    //User Input einlesen 
	private static int getInput() {
		int anz = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("Wie viele Berechnungen sollen verwendet werden um Pi zu approximieren?");
		try {
			anz = Integer.parseInt(input.nextLine());
		} //n auf max int setzen wenn der Nutzer was zu großes eingibt 
		catch (NumberFormatException e) {
			System.out.println("n ist zu gross, es wird n = max int gesetzt");
			anz = Integer.MAX_VALUE;
		}

		input.close();
		return anz;
	}
}