package fr.emion.civwars.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


/**
 * This server deals with as many clients as you want and work in non-connected mode. It uses the UDP protocol.
 * @author alexandre
 *
 */
public class UDPServer implements Runnable {
  
  private static final int PACKET_SIZE = 10;
  private DatagramSocket server;
  private boolean running;
  
  /**
   * Create the server listening to an automatically allocated port and the loopback address.
   * @throws SocketException If the socket could not be opened, or the socket could not bind to the specified local port.
   */
  public UDPServer() throws SocketException {
    this(0);
  }
  
  /**
   * Create the server with the given port and the loopback address. This port must be a positive valid port number. If given 0, this constructor does the same as the default one (listening to an automatically allocated port).
   * @param port The port to listen.
   * @throws SocketException If the socket could not be opened, or the socket could not bind to the specified local port.
   */
  public UDPServer(int port) throws SocketException {
    this(port, InetAddress.getLoopbackAddress());
  }
    
  /**
   * Create the server with the given port and the given IP address. This port must be a positive valid port number. If given 0, this constructor does the same as the default one (listening to an automatically allocated port).
   * @param port The port to listen.
   * @throws SocketException If the socket could not be opened, or the socket could not bind to the specified local port.
   */
  public UDPServer(int port, InetAddress address) throws SocketException {
    server = new DatagramSocket(port, address);
    running = true;
  }
  
  public void finalize() {
    stop();
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
    return server.getLocalAddress();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    while(running) {
      try {
        sendResponse(receivePacket());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    stop();
  }
  
  private void sendResponse(DatagramPacket clientPacket) throws IOException {
    String message = "You said : " + new String(clientPacket.getData(), 0, clientPacket.getLength());
    DatagramPacket p = new DatagramPacket(message.getBytes(), message.length(), clientPacket.getAddress(), clientPacket.getPort());
    server.send(p);
  }

  private DatagramPacket receivePacket() throws IOException {
    DatagramPacket p = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
    server.receive(p);
    return p;
  }
  
  /*private GamePacket receiveGamePacket() throws IOException {
    UDPGamePacket udpGP = GamePacketFactory.getInstance().createEmptyUDPGamePacket(new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE));
    server.receive(udpGP.getDatagramPacket());
    return udpGP;
  }*/

  public void stop() {
    running = false;
    server.close();
    server = null;
  }

}
