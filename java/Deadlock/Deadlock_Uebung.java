package Deadlock;

public class Deadlock_Uebung {
    public static void main(String[] args){
      final Object resource1 = "Ressource 1";
      final Object resource2 = "Ressource 2";

      //Thread 1 anlegen
      Thread t1 = new Thread() {
        public void run() {
          //Code der run()-Methode 
          synchronized(resource1){
            

          }
        }
      };
  
      //Hier wird der zweite Thread angelegt
      Thread t2 = new Thread(){
        public void run(){
          synchronized(resource2){
            
            
          }
        }
      };
  
      //Start der beiden Threads
      t1.start();
      t2.start();
    }
  }