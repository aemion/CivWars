package fr.emion.civwars.shared.model;

/**
 * Targetable is something that can be targeted. A targetable can move or not and has necessarily Coords.
 * @see TargetOrder
 * @see Coords
 * @author alexandre
 *
 */
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
