import java.util.Scanner;
import java.text.DecimalFormat;

public class calc_pi {

	public static void main(String[] args) {

		int anz = getInput();
		double piValue = calcPi(anz);
		System.out.println("Pi ist ungefaehr: " + piValue + ".");
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
	
	//Pi approximieren 
	private static double calcPi(double anz) {

		double pi = 0;
		for (int i = 1; i < anz; i++) {
			pi += Math.pow(-1,i+1) / (2*i - 1);
		} 
		return 4 * pi;
	} 
} 