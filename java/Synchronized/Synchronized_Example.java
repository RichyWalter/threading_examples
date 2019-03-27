package Synchronized;

/**
 * Demonstratoin der Verwendung von synchronized Blocks in Java Zwei Threads
 * sollen jeweils zwei Strings ausgeben. Dabei wird der erste String des Threads
 * einfach so ausgegeben. Die zweite Ausgabe jedoch erfolgt in einem
 * synchronized Block. Die Ausgabe der Strings "A" und "B" erfolgt also nicht
 * durcheinander sondern synchronisiert
 **/

public class Synchronized_Example
{
   public static void main(String[] args)
   {
    //Threads erzeugen und starten
      Thread th1 = new AusgabeThread("1","A");
      Thread th2 = new AusgabeThread("2","B");
      th1.start();
      th2.start();

   }

   //print Funktion, die vom Thread aufgerufen wird
   public static void print(String s1, String s2)
   {
      for(int i = 0; i < 10; i++)
      {
         System.out.print(s1);
         try
         {
            Thread.sleep(200);
         }
         catch(InterruptedException ex)
         {
         }
      }

      //Hier beginnt der synchronized Block. Code der sich darin befindet wird einzeln ausgeführt
      //Andere Threads müssen warten
      synchronized(Synchronized_Example.class)
      {
         for(int i = 0; i < 10; i++)
         {
            System.out.print(s2);
            try
            {
               Thread.sleep(200);
            }
            catch(InterruptedException ex)
            {
            }
         }
      }
   }
}