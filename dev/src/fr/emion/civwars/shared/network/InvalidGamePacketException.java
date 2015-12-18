package fr.emion.civwars.shared.network;

import fr.emion.civwars.shared.CivWarsException;


/**
 * An InvalidGamePacketException is thrown when a GamePacket is not well formed, for instance when its header is not correct.
 * @author alexandre
 * @see GamePacket
 */
@SuppressWarnings("serial")
public class InvalidGamePacketException extends CivWarsException {

  /**
   * {@inheritDoc}
   */
  public InvalidGamePacketException() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public InvalidGamePacketException(String message) {
    super(message);
  }
}
