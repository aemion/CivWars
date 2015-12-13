package fr.emion.civwars.shared.model.entities;

import java.util.LinkedList;

import fr.emion.civwars.shared.model.orders.Order;

public abstract class GameEntity {
  private int id;
  private static int ID_GENERATOR = 0; // Mb a method to change that to sync with the server
  private LinkedList<Order> orders;
  
  /**
   * Constructs a game entity with a unique id and no orders.
   */
  public GameEntity() {
    id = ID_GENERATOR++;
    orders = new LinkedList<Order>(); 
  }

  /**
   * Returns the id of the game entity.
   * @return the id of the game entity.
   */
  public int getId() {
    return id;
  }

  
  /**
   * Queues a new order for the game entity.
   * @param o The order to be queued.
   */
  public void queueOrder(Order o) {
    orders.add(o);
  }
  
  /**
   * Clears all the orders in the queue of the game entity.
   */
  public void removeQueuedOrders() {
    orders.clear();
  }
  
  /**
   * Executes the current order.
   * @see Order#execute()
   */
  public void execute() {
    if(getCurrentOrder() != null) 
      if(getCurrentOrder().execute())
        goToNextOrder();
  }
  

  private void goToNextOrder() {
    orders.poll();
  }

  private Order getCurrentOrder() {
    return orders.peekFirst();
  }
}
