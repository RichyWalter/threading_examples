//Multithreading with Java from http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_002.htm

public class multithreading_java implements Runnable {

    public static void main(String[] args) {
        System.out.println("Multithreading with Java");

        Thread t1 = new Thread( new DateCommand() );
        t1.start();

        Thread t2 = new Thread( new CounterCommand() );
        t2.start();

    }   

}