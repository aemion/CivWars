package fr.emion.civwars.shared.model.orders;


/**
 * This class represents an order. An order can be executed. The can be executed in one or in several times (called turns). 
 * @see Order#execute for more details.
 * @author alexandre
 *
 */
public abstract class Order {
  /**
   * Executes the order. The order can be executed in one or in several times (called turns). If the order is finished, this method will return true.
   * @return true if the order has finished executing, false otherwise.
   */
  public abstract boolean execute();

}
