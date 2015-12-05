package fr.emion.civwars.shared.network;

public class GamePacketFactory {
  private static GamePacketFactory instance;
  
  private GamePacketFactory() {
    
  }
  
  public static GamePacketFactory getInstance() {
    return instance;
  }
  
  public static GamePacket createPacket(String packetDescription) {
    try {
      return new GamePacket(packetDescription.getBytes());
    } catch (InvalidGamePacketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

}
