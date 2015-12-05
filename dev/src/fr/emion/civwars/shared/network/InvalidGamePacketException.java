package fr.emion.civwars.shared.network;

import fr.emion.civwars.shared.CivWarsException;

@SuppressWarnings("serial")
public class InvalidGamePacketException extends CivWarsException {

  public InvalidGamePacketException() {
    super();
  }

  public InvalidGamePacketException(String arg0) {
    super(arg0);
  }

  public InvalidGamePacketException(Throwable arg0) {
    super(arg0);
  }

  public InvalidGamePacketException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public InvalidGamePacketException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
