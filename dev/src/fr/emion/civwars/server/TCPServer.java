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
 * This server deals with as many clients as you want and work in connected mode. It uses the TCP protocol.
 * @author alexandre
 *
 */
public class TCPServer implements Runnable {

  private ServerSocket server;
  private boolean running;
  private LinkedList<ThreadServer> clients;

  private static final String LOGGER = "SERVER";
  private static final int MAX_CONNECTIONS = 100;

  /**
   * Constructs a server listening to an automatically allocated port and the loopback address.
   * @throws IOException If an I/O error occurs when opening the socket.
   */
  public TCPServer() throws IOException {
    this(0);
  }

  /**
   * Constructs a server with the given port and the loopback address. This port must be a positive valid port number. If given 0, this constructor does the same as the default one (listening to an automatically allocated port).
   * @param port The port to listen.
   * @throws IOException If an I/O error occurs when opening the socket.
   */
  public TCPServer(int port) throws IOException {
    this(port, InetAddress.getLoopbackAddress());
  }

  /**
   * Constructs a server with the given port and the given IP address. This port must be a positive valid port number. If given 0, this constructor does the same as the default one (listening to an automatically allocated port).
   * @param port The port to listen.
   * @param address The address to listen.
   * @throws IOException If an I/O error occurs when opening the socket.
   */
  public TCPServer(int port, InetAddress address) throws IOException {
    running = true;
    clients = new LinkedList<ThreadServer>();
    server = new ServerSocket(port, MAX_CONNECTIONS, address);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void finalize() {
    try {
      stop();
    } catch (IOException e) {
      Logger.getLogger(LOGGER).severe("The server can't be stopped properly : " + e);
    }
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * Stops the server and disconnect all the clients.
   * @throws IOException If an error occurs when closing a connection or the server.
   */
  public void stop() throws IOException {
    running = false;
    Iterator<ThreadServer> iterator = clients.iterator();
    while(iterator.hasNext()) {
      ThreadServer thread = iterator.next();
      thread.stop();
      iterator.remove();
    }
    server.close();
    server = null;
  }

  /**
   * Returns the port on which the server is listening.
   * @return The port on which the server is listening.
   */
  public int getListeningPort() {
    return server.getLocalPort();
  }

  /**
   * Returns the IP address of the server.
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

    /**
     * Constructs the runnable that deals with one client.
     * @param socketClient The socket of one client.
     */
    public ThreadServer(Socket socketClient) {
      this.socketClient = socketClient;
      running = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
      sendString("Connecting to the server...");
      sendString("Done !");
      while(running) {
        try {
          if(!socketClient.isOutputShutdown())
            sendString("You said to the server : " + receiveString());
          else {
            stop();
          }
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    }

    /**
     * Sends a string to the client.
     * @param message the string to send.
     */
    private void sendString(String message) {
      try {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        out.write(message);
        out.flush();
      } catch (IOException e) {
        Logger.getLogger(LOGGER).info("Can't send a string to the client : " + e);
      }
    }

    /**
     * Receives the string sent by the client.
     * @return The string received from the client.
     */
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

    /**
     * Stops this thread and disconnect the client.
     * @throws IOException If an error occurs when closing the connection.
     */
    public void stop() throws IOException {
      socketClient.close();
      clients.remove(this);
      running = false;
    }

  }
}
