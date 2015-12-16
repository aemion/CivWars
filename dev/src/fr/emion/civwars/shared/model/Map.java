package fr.emion.civwars.shared.model;

import java.util.LinkedList;

import fr.emion.civwars.shared.model.entities.GameEntity;

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
