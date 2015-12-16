package fr.emion.civwars.shared.model.orders;

public class OrderFactory {
  
  /* Returns always an order that does nothing for now */
  public static Order createOrder(String orderDescription) { 
    if(!isValidOrderString(orderDescription)) {
      return new Order() {
        public boolean execute() {
          return true;
        }
      };
    }
    
    Order o = new Order() {
      public boolean execute() {
        return true;
      }
    };
    return o;
  }

  /* Returns always false for now */
  private static boolean isValidOrderString(String desc) {
    if(desc == null)
      return false;
    if(desc.length() == 0)
      return false;
    return false;
  }
}
