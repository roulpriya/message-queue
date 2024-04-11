package org.example.consumer;

import org.example.Message;
import org.example.queue.MessageQueue;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Consumer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MessageQueue queue = (MessageQueue) registry.lookup("MessageQueue");
            MessageQueue queue2 = (MessageQueue) registry.lookup("MessageQueue2");

            //consume a message from a broker
            Message message = queue.consume();
            Message message1 = queue2.consume();
            System.out.println("Message received: " + message.getContent());
            System.out.println("Message received: " + message1.getContent());

        } catch (Exception e) {
            System.err.println("Consumer exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
