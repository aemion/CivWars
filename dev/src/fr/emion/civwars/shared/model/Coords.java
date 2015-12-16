package fr.emion.civwars.shared.model;

import java.awt.geom.Point2D;

/**
 * Coords are just a representation of a point in 2 dimensions (x and y).
 * @author alexandre
 *
 */
@SuppressWarnings("serial")
public class Coords extends Point2D.Double implements Targetable {
  /**
   * Constructs a coords with coords (0, 0) 
   */
  public Coords() {
    super();
  }

  /**
   * Constructs a coords with coords (x, y) 
   * @param x The x part of the coords.
   * @param y The y part of the coords.
   */
  public Coords(double x, double y) {
    super(x, y);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMovingTarget() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Coords getCoords() {
    return this;
  }

}
