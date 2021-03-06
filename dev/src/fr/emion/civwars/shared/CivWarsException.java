package fr.emion.civwars.shared;

/**
 * CivWarsException is the superclass of all exceptions of the CivWars project.
 * @author alexandre
 *
 */
@SuppressWarnings("serial")
public class CivWarsException extends Exception {

  /**
   * Constructs a new exception with null as its detail message. The cause is not initialized, and may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable).
   */
  public CivWarsException() {
    super();
  }

  /**
   * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable).
   * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
   */
  public CivWarsException(String message) {
    super(message);
  }
}
