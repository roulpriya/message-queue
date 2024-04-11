package org.example.producer;


import org.example.Message;
import org.example.queue.MessageQueue;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Producer {
    //Producer produces a message and sends to the queue/exchange

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MessageQueue queue = (MessageQueue) registry.lookup("MessageQueue");
            MessageQueue queue1 = (MessageQueue) registry.lookup("MessageQueue2");

            //Produce a message and send it to the queue
            Message message = new Message("Hello World");
            queue.produce(message);
            queue1.produce(message);
            System.out.println("Message sent: " + message.getContent());
        } catch (Exception e){
            System.err.println("Producer exception: "+ e);
            e.printStackTrace();
        }
    }
}
