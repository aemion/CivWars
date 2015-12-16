package fr.emion.civwars.shared.model.orders;

import fr.emion.civwars.shared.model.Targetable;

public abstract class TargetOrder extends Order {
  private Targetable target;
  
  public TargetOrder(Targetable target) {
    this.setTarget(target);
  }

  public Targetable getTarget() {
    return target;
  }

  public void setTarget(Targetable target) {
    this.target = target;
  }
}
