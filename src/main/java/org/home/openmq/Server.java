package org.home.openmq;

import org.apache.activemq.broker.BrokerService;

public class Server {
    
    private final static BrokerService broker = new BrokerService();

    public static void start()  {
        broker.setPersistent(false);
        // broker.setUseJmx(true);
        try {
            broker.addConnector("tcp://localhost:61616");
            broker.start();
        } catch (Exception e) {
            
            e.printStackTrace();
        } 
        
    }
    
    public static void stop(){

        try {
            broker.stop();
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}