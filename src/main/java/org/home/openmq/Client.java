package org.home.openmq;
import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Client {

      public static void send() {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try (Connection connection = factory.createConnection()) {
            connection.start();

            // Create a session and a queue
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("TestQueue"); 

            // Producer
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("Hello from ActiveMQ!");
            
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                producer.send(message);
            } 
            producer.send(session.createTextMessage("stop"));    
            connection.close();
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}