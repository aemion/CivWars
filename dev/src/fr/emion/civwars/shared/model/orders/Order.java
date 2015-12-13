package fr.emion.civwars.shared.model.orders;

public abstract class Order {
  /**
   * Executes the order. The order can be executed in one or in several times. If the order is finished, this method will return true.
   * @return true if the order has finished executing, false otherwise.
   */
  public abstract boolean execute();

}
