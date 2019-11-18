package com.sg.flooringmastery2.ui;

import com.sg.flooringmastery2.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author brian russick
 */
public class View {

    private UserIO io;                          

    public View(UserIO io) {                 
        this.io = io;                        // use this instance of io
    }
                                            // display menu 
    public int getMenuSelection() {
        io.print("\n  ############################################");
        io.print("  # <<Flooring Program>>");
        io.print("  # 1. Display Orders");
        io.print("  # 2. Add an Order ");
        io.print("  # 3. Edit an Order ");
        io.print("  # 4. Remove an Order ");
        io.print("  # 5. Save Current Work ");
        io.print("  # 6. Quit ");
        io.print("  #");
        System.out.println("  # Select from the above options (1 - 6)");
        io.print("  ############################################");
        return io.readInt("", 1, 6);
    }

    public boolean verifyMode() {                            // mode selection
        io.print("\nEnter Desired Mode: Training OR Production");
        String str = io.readString("");
        String production = "production";
        return !str.toLowerCase().equals(production);
    }
                                                            // banners
    public void displayOrdersBanner() {
        io.print("\n############### Display Orders ###############\n");
    }
    
    public void displayCreateOrderBanner() {
        io.print("\n################# Add Order ##################\n");
    }
    
    public void displayEditBanner() {
        io.print("\n################# Edit Order #################\n");;
    }

    public void displayEditingCompleteBanner() {
        io.print("\n########## Order editing complete! ###########\n");
    }
    
    public void displaySaveWorkBanner() {
        io.print("\n########## All work has been saved! ##########\n");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("\n################ Remove Order ################\n");;
    }
    
    public void displayRemovalCompleteBanner() {
        io.print("\n########## Order removal complete! ###########\n");
    }
    
    public void displayThankYouQuitBanner() {
        io.print("\n########### THANK YOU & GOODBYE!!! ###########\n");
    }    

    public void displayTrainingModeBanner() {
        io.print("\n  ############## Training Mode ###############\n");
    }

    public void displayProductionModeBanner() {
        io.print("\n  ############# Production Mode ##############\n");
    }

    public void displayInvalidActionBanner() {
        io.print("\n####### Training Mode - Invalid Action! ######\n");
    }  

    public void displayErrorBanner(String error) {
        io.print("\n################### ERROR ####################\n");
        io.print(error);
        io.print("################### ERROR ####################\n");
    }    

    public void displayUnknownCommandBanner() {
        io.print("\n############ UNKNOWN COMMAND ERROR ############\n");
    }
    
    public void getEntireOrderList(List<Order> list) { 
        list.forEach((o) -> {            // required details for all orders
        io.print("\nOrder #: " + o.getOrderNum() + " | " + "Customer: " + 
        o.getCustomer() + " | " + "State: " + o.getState() + " | " + 
        "Tax Rate: $" + o.getTaxRate() + " | " + "\n" + "Product: " + 
        o.getProduct() + " | " + "Cost per sq ft: $" + o.getCostPerSqFt() +
        " | " + "Labor cost per sq ft: $" + o.getLabCostPerSqFt() + " | " +
        "Area: " + o.getArea() + " | " + "\n" + "Material cost: $" + 
        o.getMaterialCost() + " | " + "Labor cost: $" + o.getLabCost() +
        " | " + "Tax: $" + o.getTax() + " | " + "\n" + "Total cost: $" + 
        o.getTotalCost());
            }
        );
    }

    public Order getOrderDetailsNew() {      // ask user for new order details
        io.print("Customer: "); String customer = io.readString("");
        io.print("State: "); String state = io.readString("");
        io.print("Product: "); String product = io.readString("");
        io.print("Area: "); BigDecimal area = io.readBigDecimal("");
        io.print("Date (MMDDYYYY): "); LocalDate date = io.readDate("");

        Order o;
        o = new Order();
        o.setCustomer(customer);        // set details for new order
        o.setState(state);
        o.setProduct(product);
        o.setArea(area);
        o.setDate(date);

        io.print("Customer: " + o.getCustomer()+ " | " + "State: " + 
        o.getState()+ " | " + "Product: " + o.getProduct()+ " | " + 
        "Area: " + o.getArea()+ " | " + "Date: " + o.getDate());

        return o;
    }

    public void getAddOrder(Order o) {   // required details for all orders
        io.print("\nCustomer: " + o.getCustomer() + " | " + "State: " + 
        o.getState() + " | " + "Tax Rate: $" + o.getTaxRate() + " | " + 
        "Product: " + o.getProduct() + " | " + "\n" + "Cost per sq ft: $" +
        o.getCostPerSqFt() + " | " + "Labor cost per sq ft: $" + 
        o.getLabCostPerSqFt() + " | " + "Area: " + o.getArea() + 
        " | " + "\n" + "Material cost: $" + o.getMaterialCost() + " | " + 
        "Labor cost: $" + o.getLabCost() + " | " + "Tax: $" + o.getTax() +
        " | " + "\n" + "Total cost: $" + o.getTotalCost());
    }
    
    public Order getEditOrder(Order o) {             // edit order details
        io.print("\n***Press 'Enter' to skip entry field***\n");                                             
        io.print("Customer: " + o.getCustomer());   // print customer name
        io.print("Updated Customer Name: ");
                                                // enter updated customer name
        String updatedCustomer = io.readString(o.getCustomer());
        if (updatedCustomer == null) {            // if field is null / empty
            o.setCustomer(o.getCustomer()); // use existing name
        } else if (updatedCustomer.trim().equals("")) {
            o.setCustomer(o.getCustomer());
        } else {              // otherwise use the newly inputed customer name
            o.setCustomer(updatedCustomer); 
        }
        
        io.print("State: " + o.getState());                 // print state
        io.print("Updated State: "); 
        String updatedState;
        updatedState = io.readString(o.getState());  // enter updated state
       
        if (updatedState.trim().equals("")) {              // if field is empty
            o.setState(o.getState());             // use existing state
        } else {                       // otherwise use the newly inputed state
            o.setState(updatedState);}
        
        io.print("Product: " + o.getProduct());            // print product
        io.print("Updated Product: "); 
                                                       // enter updated product
        String updatedProduct = io.readString(o.getProduct());
        
        if (updatedProduct.trim().equals("")) {            // if field is empty
            o.setProduct(o.getProduct());       // use existing product
        } else {                     // otherwise use the newly inputed product
            o.setProduct(updatedProduct);}

        io.print("Area: " + o.getArea() + " sq ft");          // print area
        io.print("Updated Area: ");
                                                          // enter updated area
        String updatedArea = io.readString("" + o.getArea() + "");
        if (updatedArea.trim().equals("")) {               // if field is empty
            o.setArea(o.getArea());               // use existing state
        } else {                        // otherwise use the newly inputed area
            o.setArea(new BigDecimal(updatedArea));}
        
        io.print("Date: " +o.getDate());                      // print date
        io.print("Updated Date: ");
                                                          // enter updated date
        LocalDate updatedDate = io.readDate("" + o.getDate() + "");
        if (updatedDate == null) {        // if field is null use existing date
            o.setDate(o.getDate());
        } else {                        // otherwise use the newly inputed date
        o.setDate(updatedDate);}
        
        io.print(("Save changes? (y/n)"));
        String str = io.readString("");                         // enter y or n
        String y = "y";

        if (str.toLowerCase().equals(y)) {    // the app only saves changes to
            return o; // order file if 'Option 5' is chosen from main menu 
        } else {        // before exiting app, so here y or n returns the same
            return o; // 'order editing complete!" banner in this case for
        }                // the sake of simplicity / relevance
    }

    public boolean saveOrder() {
        io.print("\nSave order to memory? (y/n)");
        String str = io.readString("");                        // enter y or n
        String y = "y";
        return str.toLowerCase().equals(y);       // if y save order to memory
    }

    public LocalDate getUserDate() {
             io.print("Enter date (MMDDYYYY): ");
      return io.readDate("");
    
    }

    public Integer getOrderNum() {
             io.print("Order #: ");
      return io.readInt(""); 
    }

    public boolean askUserIfWantToRemoveTheOrder() {
                      io.print("Confirm REMOVE order? (y/n) ");
        String answer = io.readString("");
        String yes = "y";
        if (answer.toLowerCase().equals(yes)) {
            return true;
        } else {
           return false;
        } 
    }    
    
    public String confirmOrderNum() {
        io.print("Confirm Order #: ");
        return io.readString("");
    }
    
    public LocalDate userDateSelection() {
        io.print("Enter date (MMDDYYYY): ");
        return io.readDate("");
    }    
}