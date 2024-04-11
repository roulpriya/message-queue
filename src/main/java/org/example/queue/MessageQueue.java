package org.example.queue;

import org.example.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageQueue extends Remote {
    void produce(Message message) throws RemoteException;
    Message consume() throws RemoteException;
}
