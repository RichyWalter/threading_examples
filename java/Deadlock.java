
/**
 * Demonstratoin eines Deadlocks in Java. Zwei Threads binden Ressourcen ohne sie wieder frei zu geben.
 * Während eine Ressource gebunden ist, versucht der andere Thread diese Ressource für sich zu binden. 
 * Ergebniss dieses Versuchs ist eine Verklemmung, die dafür sorgt, dass das Programm nie terminiert.
 **/
public class Deadlock {
    public static void main(String[] args){
      //Zwei Ressourcen werden angelegt
      //Diese Ressourcen sollen dann von den zwei Threads gebunden werden
      final Object resource1 = "Ressource 1";
      final Object resource2 = "Ressource 2";
      //Hier wird der erste Thread erzeugt
      //Dieser versucht zunächst resource1 zu binden, und anschliessend ressource 2
      Thread t1 = new Thread() {
        public void run() {
          //resource 1 wird von Thread 1 gebunden
          synchronized(resource1){
            System.out.println("Thread 1: resource 1 gebunden");
            //Es folgt eine kleine Pause um Dinge wie File IO zu simulieren.
            //Dies gibt dem anderen Thread die Möglichkeit zu starten. und mit der Ausführung zu beginnen.
            try{
              Thread.sleep(50); // Sleep 50 ms
            } catch (InterruptedException e) {}
  
            //Warten bis resource2 durch den ersten Thread gebunden werden kann
            synchronized(resource2){
              System.out.println("Thread 1: resource 2 gebunden");
            }
          }
        }
      };
  
      //Hier wird der zweite Thread angelegt
      //Dieses bindet zunächst ressource2 und versucht anschliessend ressource1 zu binden 
      Thread t2 = new Thread(){
        public void run(){
          //ressource2 wird gebunden
          synchronized(resource2){
            System.out.println("Thread 2: resource 2 gebunden");
            //Pause wie bei Thread 1
            try{
              Thread.sleep(50); // Sleep 50 ms
            } catch (InterruptedException e){}
  
            //hier wird versucht resource1 zu binden, diese wird jedoch vom ersten Thread blockiert.
            //Die Folge ist hierbei ein Deadlock, und das Programm terminiert nicht mehr
            synchronized(resource1){
              System.out.println("Thread 2: resource 1 gebunden");
            }
          }
        }
      };
  
      //Start der beiden Threads
      t1.start();
      t2.start();
    }
  }