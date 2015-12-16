package fr.emion.civwars.shared.model;

public interface Targetable {
  /**
   * Returns true if the targetable can move.
   * @return true if the targetable can move, false otherwise.
   */
  public boolean isMovingTarget();
  
  /**
   * Returns the coords of the targetable.
   * @return the coords of the targetable.
   */
  public Coords getCoords();
}
