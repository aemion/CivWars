package fr.emion.civwars.shared.model.orders;
/**
 * A factory to generate orders from simple Strings (and maybe integers/doubles/Enum). If the input is not correct, always returns an order that does nothing.
 * Work in progress...
 * @author alexandre
 *
 */
public class OrderFactory {
  
  /**
   * Create an order from a String. Always returns an order that does nothing for now.
   * @param orderDescription the string description of the order.
   * @return the order created.
   */
  public static Order createOrder(String orderDescription) { 
    if(!isValidOrderString(orderDescription)) {
      return new Order() {
        public boolean execute() {
          return true;
        }
      };
    }
    
    Order order = new Order() {
      public boolean execute() {
        return true;
      }
    };
    return order;
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
