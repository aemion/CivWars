package fr.emion.civwars.shared.model;

import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Coords extends Point2D.Double implements Targetable {
  public Coords() {
    super();
  }

  public Coords(double x, double y) {
    super(x, y);
    // TODO Auto-generated constructor stub
  }

  @Override
  public boolean isMovingTarget() {
    return false;
  }

  @Override
  public Coords getCoords() {
    return this;
  }

}
