package fr.emion.civwars.shared;

@SuppressWarnings("serial")
public class CivWarsException extends Exception {

  public CivWarsException() {
    super();
  }

  public CivWarsException(String arg0) {
    super(arg0);
  }

  public CivWarsException(Throwable arg0) {
    super(arg0);
  }

  public CivWarsException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public CivWarsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
