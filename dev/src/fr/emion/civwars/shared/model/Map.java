package fr.emion.civwars.shared.model;

import java.util.LinkedList;

import fr.emion.civwars.shared.model.entities.GameEntity;

/**
 * The map of the game. Game entities can be added to the map. 
 * It can execute one turn (in the sense of {@link fr.emion.civwars.shared.model.orders.Order} turns) of all the game entities on the map.
 * @author alexandre
 *
 */
public class Map {
  private LinkedList<GameEntity> entities;
  
  public Map() {
    entities = new LinkedList<GameEntity>();
  }
  
  public void executeOneTurn() {
    for(GameEntity e : entities)
      e.execute();
  }
  
  public void addGameEntity(GameEntity e) {
    entities.add(e);
  }
  
}
