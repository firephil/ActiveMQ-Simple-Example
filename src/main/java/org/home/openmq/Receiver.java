package org.home.openmq;

import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Receiver {

    public static void start() {
        try  {
            Thread.sleep(100);
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Destination destination = session.createTopic("command");
            MessageConsumer consumer = session.createConsumer(session.createQueue("TestQueue"));
            connection.start();

            while (true) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    var msg = textMessage.getText();
                    System.out.println("Received message: " + msg);
                                     
                    if (msg.equals("stop")) {
                        connection.close();
                        Server.stop();
                        break;
                    }
                } else {
                    System.out.println("Received non-text message: " + message);
                }
            }
        } catch (JMSException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
