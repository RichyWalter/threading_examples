public class AusgabeThread extends Thread
{
   private String s1;
   private String s2;

   public AusgabeThread(String s1, String s2)
   {
      this.s1 = s1;
      this.s2 = s2;
   }

   public void run()
   {
      Synchronized_Example.print(s1,s2);
   }
}
