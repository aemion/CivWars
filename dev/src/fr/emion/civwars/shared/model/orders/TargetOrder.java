package fr.emion.civwars.shared.model.orders;

import fr.emion.civwars.shared.model.Targetable;

/**
 * This class represents an Order with a target.
 * @see Order
 * @author alexandre
 *
 */
public abstract class TargetOrder extends Order {
  private Targetable target;
  
  /**
   * Constructs an order with a target.
   * @param target the target of the order.
   */
  public TargetOrder(Targetable target) {
    this.setTarget(target);
  }

  /**
   * Returns the target of the order.
   * @return the target of the order.
   */
  public Targetable getTarget() {
    return target;
  }

  /**
   * Sets the target of the order.
   * @param target the new target of the order.
   */
  public void setTarget(Targetable target) {
    this.target = target;
  }
}
