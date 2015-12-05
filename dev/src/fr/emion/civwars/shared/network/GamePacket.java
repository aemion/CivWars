package fr.emion.civwars.shared.network;


import java.util.Arrays;

public class GamePacket {

  private static final int HEADER_SIZE = 6;
  
  GamePacket(byte[] data) throws InvalidGamePacketException {
    setData(data);
  }
  
  private byte[] data;
  
  public byte[] getHeader() {
    return Arrays.copyOfRange(data, 0, HEADER_SIZE);
  }
  
  public byte[] getData() {
    return Arrays.copyOfRange(data, HEADER_SIZE, Math.max(getDataLength(), HEADER_SIZE)); // Max if there was a problem at the creation and the header somehow is less than HEADER_SIZE bytes
  }
  
  public int getDataLength() {
    return data.length;
  }
  
  public byte getPacketType() {
    return data[0];
  }
  
  public byte getGameId() {
    return data[1];
  }
  
  public byte getSenderId() {
    return data[2];
  }
  
  public short getGameEntityId() {
    short geid = (short) ((((byte)data[3] & 0xFF) << 8) | (byte)data[4] & 0xFF);
    return geid;
  }
  
  public byte getAction() {
    return data[5];
  }
  
  protected void setData(byte[] data) throws InvalidGamePacketException {
    if(data.length < HEADER_SIZE)
      throw new InvalidGamePacketException("The packet is too short (the header and so the packet needs to be at least " + HEADER_SIZE + " bytes)");
    this.data = data;
  }
}
