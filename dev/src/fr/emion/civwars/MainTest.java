package fr.emion.civwars;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

import fr.emion.civwars.server.TCPServer;
import fr.emion.civwars.server.UDPServer;
import fr.emion.civwars.shared.network.GamePacket;
import fr.emion.civwars.shared.network.InvalidGamePacketException;

public class MainTest {
  public static void main(String[] args) throws IOException, InvalidGamePacketException {
    /*UDPServer server = new UDPServer(12000);
    new Thread(server).start();
    System.out.println(server.getListeningPort());
    System.out.println(server.getInetAdress());
    String message;
    Scanner sc = new Scanner(System.in);
    boolean running = true;
    while(running) {
      
      message = sc.nextLine();
      envoyerMessage(message);
    }
    sc.close();*/
    TCPServer server = new TCPServer(12000);
    new Thread(server).start();
    /*byte[] data = {1, 2, 3, 4, 5, 6, 7, 8};
    byte b = (byte)255;
    System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));

    short geid = (short) ((((byte)data[3] & 0xFF) << 8) | (byte)data[4] & 0xFF);

    System.out.println(String.format("%8s", Integer.toBinaryString((byte)data[3] & 0xFF)).replace(' ', '0'));
    System.out.println(String.format("%8s", Integer.toBinaryString((byte)data[4] & 0xFF)).replace(' ', '0'));
    System.out.println(String.format("%16s", Integer.toBinaryString(geid & 0xFFFF)).replace(' ', '0'));
    System.out.println(geid);*/
  }
  private static void envoyerMessage(String message) {
    DatagramSocket socket;
    try {
      socket = new DatagramSocket();
      envoyerRequete(socket, message);
      System.out.println(lireResultat(socket));
      socket.close();
    } catch (Exception e) {
      System.out.println("Erreur : "+e);
    }
  }

  private static void envoyerRequete(DatagramSocket socket, String message) throws java.io.IOException,java.net.UnknownHostException {
    byte[] buffer=message.getBytes();
    DatagramPacket paquetUDP=new DatagramPacket(buffer,
        buffer.length,
        InetAddress.getByName("127.0.0.1"),
        12000);
    socket.send(paquetUDP);
  }

  private static String lireResultat(DatagramSocket socket) throws java.io.IOException {
    DatagramPacket paquetUDP=new DatagramPacket(new byte[512], 512);
    socket.receive(paquetUDP);
    String resultat=new String(paquetUDP.getData(),0,paquetUDP.getLength());
    return resultat;
  }
}