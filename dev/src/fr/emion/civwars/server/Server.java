package fr.emion.civwars.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;


/**
 * This server deals with as many clients as you want and work in connected mode
 * @author alexandre
 *
 */
public class Server implements Runnable {

  private ServerSocket server;
  private boolean running;
  private LinkedList<ThreadServer> clients;
  private final String LOGGER = "SERVER";

  /**
   * Create the server listening to an automatically allocated port.
   * @throws IOException If an I/O error occurs when opening the socket.
   */
  public Server() throws IOException {
    this(0);
  }
  
  /**
   * Create the server with the given port. This port must be a positive valid port number. If given 0, this constructor does the same as the default one (listening to an automatically allocated port).
   * @param port The port to listen.
   * @throws IOException If an I/O error occurs when opening the socket.
   */
  public Server(int port) throws IOException {
    running = true;
    clients = new LinkedList<ThreadServer>();
    server = new ServerSocket(port);
  }
  
  @Override
  public void finalize() {
    try {
      stop();
    } catch (IOException e) {
      Logger.getLogger(LOGGER).severe("The server can't be stopped properly : " + e);
    }
  }

  @Override
  public void run() {
    while(running) {
      Socket newSocket;
      try {
        newSocket = server.accept();
        ThreadServer thread = new ThreadServer(newSocket);
        clients.add(thread);
        new Thread(thread).start();
      } catch (IOException e) {
        Logger.getLogger(LOGGER).warning("The server can't accept the new connection : " + e);
      }

    }

  }

  public void stop() throws IOException {
    running = false;
    Iterator<ThreadServer> iterator = clients.iterator();
    while(iterator.hasNext()) {
      ThreadServer thread = iterator.next();
      thread.stop();
      iterator.remove();
    }
    server.close();
  }
  
  /**
   * Get the port on which the server is listening.
   * @return The port on which the server is listening.
   */
  public int getListeningPort() {
    return server.getLocalPort();
  }
  
  /**
   * Get the IP address of the server.
   * @return The InetAddress representing the IP address of the server.
   */
  public InetAddress getInetAdress() {
    return server.getInetAddress();
  }

  /**
   * Thread that deals with one client.
   * @author alexandre
   *
   */
  private class ThreadServer implements Runnable {
    private Socket socketClient;
    private boolean running;

    public ThreadServer(Socket socketClient) {
      this.socketClient = socketClient;
      running = true;
    }

    @Override
    public void run() {
      sendString("Connecting to the server...");
      sendString("Done !");
      while(running) {
        sendString("You said to the server : " + receiveString());
      }

    }

    private void sendString(String message) {
      try {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        out.write(message);
        out.flush();
      } catch (IOException e) {
        Logger.getLogger(LOGGER).info("Can't send a string to the client : " + e);
      }
    }

    private String receiveString() {
      String message = null;
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        message = in.readLine();
      } catch (IOException e) {
        Logger.getLogger(LOGGER).info("Can't receive a string from the client : " + e);
      }
     
      return message;

    }

    public void stop() throws IOException {
      socketClient.close();
      clients.remove(this);
      running = false;
    }

  }
}
