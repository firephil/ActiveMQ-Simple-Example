package org.home.openmq;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;

public class OpenMQ {

    
        public static void main(String[] args) throws Exception {

            ExecutorService executor = Executors.newFixedThreadPool(5); // 5 threads         
            executor.execute(() -> Server.start());
            executor.execute(() -> Receiver.start());
            executor.execute(() -> Client.send());

            executor.shutdown();
      }
}