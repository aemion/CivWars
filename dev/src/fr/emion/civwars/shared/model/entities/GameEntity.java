package fr.emion.civwars.shared.model.entities;

import java.util.LinkedList;

import fr.emion.civwars.shared.model.Coords;
import fr.emion.civwars.shared.model.Targetable;
import fr.emion.civwars.shared.model.orders.Order;


/**
 * Abstract class representing any game entity (buildings, units...). 
 * A GameEntity has a unique id.
 * A GameEntity can receive orders which are executed from the oldest to the newest. 
 * @author alexandre
 */
public abstract class GameEntity implements Targetable {
  private int id;
  private static int ID_GENERATOR = 0; // Mb a method to change that to sync with the server
  private LinkedList<Order> orders;
  private Coords position;
  
  /**
   * Constructs a game entity with a unique id, no orders and the default position.
   */
  public GameEntity() {
    this(new Coords()); 
  }
  
  /**
   * Constructs a game entity with a unique id, no orders and a given position.
   * @param position the position of the game entity.
   */
  public GameEntity(Coords position) {
    id = ID_GENERATOR++;
    orders = new LinkedList<Order>();
    setPosition(position);
  }

  /**
   * Returns the id of the game entity.
   * @return the id of the game entity.
   */
  public int getId() {
    return id;
  }
  
  /**
   * {@inheritDoc}
   * This is equivalent to {@link #getPosition}.
   */
  @Override
  public Coords getCoords() {
    return getPosition();
  }
  

  /**
   * Returns the position of the game entity.
   * @return the position of the game entity.
   */
  public Coords getPosition() {
    return position;
  }

  /**
   * Sets the position of the game entity.
   * @param position the new position of the game entity.
   */
  public void setPosition(Coords position) {
    this.position = position;
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
