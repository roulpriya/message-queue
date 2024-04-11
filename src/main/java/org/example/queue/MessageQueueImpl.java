package org.example.queue;

import org.example.Message;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;


public class MessageQueueImpl extends UnicastRemoteObject implements MessageQueue {
    private static final long serialVersionUID = 1L;
    private final Queue<Message> queue;

    protected MessageQueueImpl() throws RemoteException {
        super();
        this.queue = new LinkedList<>();
    }

    @Override
    public void produce(Message message) {
        queue.add(message);
    }

    @Override
    public Message consume() {
        return queue.poll();
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            MessageQueueImpl messageQueue = new MessageQueueImpl();
            Naming.rebind("MessageQueue", messageQueue);
            System.out.println("Message Queue 1");
            Naming.rebind("MessageQueue2", messageQueue);
            System.out.println("Message Queue 2");
        } catch (Exception e) {
            System.err.println("Message Queue exception: " + e.toString());
        }
    }

}
